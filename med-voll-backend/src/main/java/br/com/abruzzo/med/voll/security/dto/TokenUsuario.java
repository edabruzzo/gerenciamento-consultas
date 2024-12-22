package br.com.abruzzo.med.voll.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenUsuario {
    private final Long idUsuario;
    private final String papel;
    private final String nome;
    private final String cpf;
}
