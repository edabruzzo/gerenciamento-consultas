package br.com.abruzzo.med.voll.dominio.medico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaDto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "Cadastro de Pessoa Física inválido")
        String cpf,

        @Valid
        @Past(message = "Não é possível cadastrar data de nascimento futura")
        @NotNull(message = "Data de nascimento (dd/MM/yyyy) é obrigatória")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid DadosEnderecoDto endereco) {

}


