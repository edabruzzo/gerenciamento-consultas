package br.com.abruzzo.med.voll.api;

import br.com.abruzzo.med.voll.core.model.Resposta;
import br.com.abruzzo.med.voll.exceptions.MensagemErroEnum;
import br.com.abruzzo.med.voll.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErroApi implements BaseApi {

    @ExceptionHandler({EntityNotFoundException.class, RecursoNaoEncontradoException.class})
    public ResponseEntity tratarErro404(){
        return enviarResposta(new Resposta(MensagemErroEnum.ENTIDADE_NAO_ENCONTRADA),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors().stream().map(DadosErroValidacao::new).toList();
        return enviarResposta(new Resposta(erros),
                HttpStatus.BAD_REQUEST);
    }


    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
