package br.com.abruzzo.med.voll.dominio.medico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaAtualizarDto(
                        String nome,
                        @Email(message = "{email.invalido}")
                        String email,
                        @CPF(message = "{cpf.invalido}")
                        String cpf,
                        @Valid
                        @Past(message = "{dataNascimento.invalida}")
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                        LocalDate dataNascimento,

                        @Valid
                        DadosEnderecoAtualizarDto endereco) {
}

