package br.com.abruzzo.med.voll.dominio.medico;

import jakarta.validation.constraints.Pattern;


public record DadosEnderecoAtualizarDto (
        String logradouro,
        String bairro,
        @Pattern(regexp = "\\d{5}-\\d{3}")
        String cep,
        String cidade,
        String uf,
        String complemento,

        String numero) {
}

