package br.com.abruzzo.med.voll.core.model.mappers;

import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;

import java.util.List;

public interface BaseMapper<E extends EntidadeBase<?>, D extends DtoBase> {
    D toDto(E entity);

    List<D> toDto(List<E> listEntity);

    E toEntity(D dto);

}

