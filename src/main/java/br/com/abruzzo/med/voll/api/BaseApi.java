package br.com.abruzzo.med.voll.api;

import br.com.abruzzo.med.voll.core.model.Resposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BaseApi {
    default <T> ResponseEntity<Resposta<T>> ok(T conteudoResposta) {
        return new ResponseEntity(new Resposta(conteudoResposta), HttpStatus.OK);
    }

    default ResponseEntity<Resposta> ok() {
        return new ResponseEntity(new Resposta(), HttpStatus.OK);
    }

    default <T> ResponseEntity<Resposta<T>> enviarResposta(T resposta, HttpStatus status) {
        return ResponseEntity.status(status).body(new Resposta(resposta, status));
    }
}
