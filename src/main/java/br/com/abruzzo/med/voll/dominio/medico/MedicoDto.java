package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

public record MedicoDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank @CPF
        String cpf,

        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        EspecialidadeEnum especialidade,

        @Valid
        @NotNull
        DadosEnderecoDto endereco) implements DtoBase {
}
