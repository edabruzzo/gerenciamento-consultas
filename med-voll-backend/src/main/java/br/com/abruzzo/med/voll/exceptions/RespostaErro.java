package br.com.abruzzo.med.voll.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
public class RespostaErro {
    private int status;
    private List<String> messages;
    private LocalDateTime timestamp;
    private String error;
    private String path;

    public RespostaErro() {
        this(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public RespostaErro(HttpStatus status) {
        this.atribuirStatus(status);
    }

    public RespostaErro(HttpStatus status, String mensagem, String pathRequest) {
        this(status);
        this.messages = Collections.singletonList(mensagem);
        this.path = pathRequest;
    }

    public RespostaErro(HttpStatus status, List<String> mensagens, String pathRequest) {
        this(status);
        this.messages = Collections.unmodifiableList(mensagens);
        this.path = pathRequest;
    }

    public RespostaErro(HttpStatus status, List<String> mensagens) {
        this(status);
        this.messages = Collections.unmodifiableList(mensagens);
    }

    private void atribuirStatus(HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        if (status.isError()) {
            this.error = status.getReasonPhrase();
        }

    }

    @Generated
    public int getStatus() {
        return this.status;
    }

    @Generated
    public List<String> getMessages() {
        return this.messages;
    }

    @Generated
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    @Generated
    public String getError() {
        return this.error;
    }

    @Generated
    public String getPath() {
        return this.path;
    }

    @Generated
    public String toString() {
        int var10000 = this.getStatus();
        return "RespostaErro(status=" + var10000 + ", messages=" + this.getMessages() + ", timestamp=" + this.getTimestamp() + ", error=" + this.getError() + ", path=" + this.getPath() + ")";
    }
}
