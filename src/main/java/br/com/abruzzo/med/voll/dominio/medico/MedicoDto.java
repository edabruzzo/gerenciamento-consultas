package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.core.model.dto.DtoBase;

public record MedicoDto(String nome, String email, String cpf, EspecialidadeEnum especialidade, DadosEnderecoDto endereco) implements DtoBase {
}
