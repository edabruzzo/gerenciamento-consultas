package br.com.abruzzo.med.voll.api;

import br.com.abruzzo.med.voll.core.model.Resposta;
import br.com.abruzzo.med.voll.exceptions.MensagemErroEnum;
import br.com.abruzzo.med.voll.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErroApi implements BaseApi {

    @ExceptionHandler({EntityNotFoundException.class, RecursoNaoEncontradoException.class})
    public ResponseEntity tratarErro404(){
        return enviarResposta(new Resposta(MensagemErroEnum.ENTIDADE_NAO_ENCONTRADA), HttpStatus.BAD_REQUEST);
    }
}
