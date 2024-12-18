package br.com.abruzzo.med.voll.dominio.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
