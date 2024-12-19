package br.com.abruzzo.med.voll.dominio.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDto (
        @NotBlank(message = "{logradouro.obrigatorio}")
        String logradouro,
        @NotBlank(message = "{bairro.obrigatorio}")
        String bairro,
        @NotBlank(message = "{cep.obrigatorio}")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "{cep.invalido}")
        String cep,
        @NotBlank(message = "{cidade.obrigatoria}")
        String cidade,
        @NotBlank(message = "{uf.obrigatoria}")
        String uf,
        String complemento,

        @NotBlank(message = "{numero.obrigatorio}")
        String numero) {
}
