package br.com.abruzzo.med.voll.security.exceptions.enums;

public enum MensagemErroEnum {
    ERRO_CRIACAO_TOKEN("Erro na criação do token JWT");

    private final String descricao;

    MensagemErroEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
