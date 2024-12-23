package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.core.model.mappers.BaseMapper;
import br.com.abruzzo.med.voll.dominio.entidades.Endereco;
import br.com.abruzzo.med.voll.dominio.entidades.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper extends BaseMapper<Endereco, DadosEnderecoDto>{

    Endereco dtoAtualizarToEntity(DadosEnderecoAtualizarDto dto);
}
