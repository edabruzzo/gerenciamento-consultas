package br.com.abruzzo.med.voll.security.dto;

import br.com.abruzzo.med.voll.security.model.PapelSistemaEnum;

public record DadosAutenticacaoDto(String login, String senha, PapelSistemaEnum papel) {
}
