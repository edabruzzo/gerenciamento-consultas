package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;

    private String cidade;

    private String uf;

    private String complemento;


}
