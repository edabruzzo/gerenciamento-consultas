package br.com.abruzzo.med.voll.dominio.paciente;

import br.com.abruzzo.med.voll.dominio.entidades.Pessoa;
import jakarta.validation.Valid;

public record PacienteDto(@Valid Pessoa pessoa, String profissao) {
}
