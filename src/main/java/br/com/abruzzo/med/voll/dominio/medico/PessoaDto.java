package br.com.abruzzo.med.voll.dominio.medico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaDto(@NotBlank
                        String nome,
                        @NotBlank
                        @Email
                        String email,
                        @NotBlank @CPF
                        String cpf,
                        @Valid @Past @NotNull
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                        LocalDate dataNascimento,

                        @Valid
                        @NotNull
                        DadosEnderecoDto endereco) {
}
