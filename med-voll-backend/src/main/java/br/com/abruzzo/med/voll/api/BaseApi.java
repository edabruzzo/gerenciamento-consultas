package br.com.abruzzo.med.voll.api;

import br.com.abruzzo.med.voll.core.model.Resposta;
import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface BaseApi<D extends DtoBase> {
    default ResponseEntity<Resposta<D>> ok(D conteudoResposta) {
        return new ResponseEntity(new Resposta(conteudoResposta), HttpStatus.OK);
    }

    default ResponseEntity<Resposta> ok() {
        return new ResponseEntity(new Resposta(), HttpStatus.OK);
    }

    default <T> ResponseEntity<Resposta<T>> enviarResposta(T resposta, HttpStatus status) {
        return ResponseEntity.status(status).body(new Resposta(resposta, status));
    }

    default <T> ResponseEntity<Resposta<T>> enviarRespostaRecursoCriado(T resposta, URI uri) {
        return ResponseEntity.created(uri).body(new Resposta(resposta));
    }

}
