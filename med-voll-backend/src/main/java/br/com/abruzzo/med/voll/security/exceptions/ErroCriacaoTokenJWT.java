package br.com.abruzzo.med.voll.security.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;

public class ErroCriacaoTokenJWT extends RuntimeException {
    public ErroCriacaoTokenJWT(RuntimeException e, String mensagemErro) {
        super(mensagemErro, e);
    }
}
