package br.com.abruzzo.med.voll.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resposta<T> {
    private int status;
    private LocalDateTime timestamp;
    private T result;

    public Resposta() {
        this(HttpStatus.OK);
    }

    private Resposta(HttpStatus status) {
        this.atribuirStatus(status);
    }

    public Resposta(T result) {
        this();
        this.result = result;
    }

    public Resposta(T result, HttpStatus status) {
        this(status);
        this.result = result;
    }

    private void atribuirStatus(HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
    }

    @Generated
    public int getStatus() {
        return this.status;
    }

    @Generated
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    @Generated
    public T getResult() {
        return this.result;
    }

    @Generated
    public String toString() {
        int var10000 = this.getStatus();
        return "Resposta(status=" + var10000 + ", timestamp=" + this.getTimestamp() + ", result=" + this.getResult() + ")";
    }
}
