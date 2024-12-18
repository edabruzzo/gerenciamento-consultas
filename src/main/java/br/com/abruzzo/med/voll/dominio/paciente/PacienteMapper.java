package br.com.abruzzo.med.voll.dominio.paciente;

import br.com.abruzzo.med.voll.core.model.mappers.BaseMapper;
import br.com.abruzzo.med.voll.dominio.entidades.Paciente;
import br.com.abruzzo.med.voll.dominio.medico.PessoaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses={PessoaMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PacienteMapper extends BaseMapper<Paciente,PacienteDto> {
}
