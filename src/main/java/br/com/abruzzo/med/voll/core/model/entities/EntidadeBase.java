package br.com.abruzzo.med.voll.core.model.entities;

public interface EntidadeBase<ID>{
    ID getId();

    void atualizar(EntidadeBase dadosParaAlteracao);
}

