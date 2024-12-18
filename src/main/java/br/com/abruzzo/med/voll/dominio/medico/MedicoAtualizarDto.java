package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoAtualizarDto(@Valid
                                 PessoaAtualizarDto pessoa,
                                 @Pattern(regexp = "\\d{4,6}")
                                 String crm,
                                 EspecialidadeEnum especialidade

                                 ) implements DtoBase {
}

