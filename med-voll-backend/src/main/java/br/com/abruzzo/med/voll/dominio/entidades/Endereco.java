package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco implements EntidadeBase {

    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;

    private String cidade;

    private String uf;

    private String complemento;


    @Override
    public Object getId() {
        return null;
    }

    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if (dadosParaAlteracao == null) {
            return;
        }
        Endereco dadosAlteracao = (Endereco) dadosParaAlteracao;
        // Atualiza cada campo somente se o valor for diferente
        if (dadosAlteracao.getLogradouro() != null && !Objects.equals(dadosAlteracao.getLogradouro(), this.logradouro)) {
            this.logradouro = dadosAlteracao.getLogradouro();
        }
        if (dadosAlteracao.getNumero() != null && !Objects.equals(dadosAlteracao.getNumero(), this.numero)) {
            this.numero = dadosAlteracao.getNumero();
        }
        if (dadosAlteracao.getCep() != null && !Objects.equals(dadosAlteracao.getCep(), this.cep)) {
            this.cep = dadosAlteracao.getCep();
        }
        if (dadosAlteracao.getBairro() != null && !Objects.equals(dadosAlteracao.getBairro(), this.bairro)) {
            this.bairro = dadosAlteracao.getBairro();
        }
        if (dadosAlteracao.getCidade() != null && !Objects.equals(dadosAlteracao.getCidade(), this.cidade)) {
            this.cidade = dadosAlteracao.getCidade();
        }
        if (dadosAlteracao.getUf() != null && !Objects.equals(dadosAlteracao.getUf(), this.uf)) {
            this.uf = dadosAlteracao.getUf();
        }
        if (dadosAlteracao.getComplemento() != null && !Objects.equals(dadosAlteracao.getComplemento(), this.complemento)) {
            this.complemento = dadosAlteracao.getComplemento();
        }
    }
}
