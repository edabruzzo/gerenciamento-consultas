package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.api.CrudBaseApi;
import br.com.abruzzo.med.voll.core.model.RespostaPaginada;
import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.core.model.mappers.BaseMapper;
import br.com.abruzzo.med.voll.core.service.CrudBaseService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/medicos")
public class MedicoApi implements CrudBaseApi<Medico, MedicoDto, Long>{

    private final MedicoService medicoService;
    private final MedicoMapper medicoMapper;

    public MedicoApi(MedicoService medicoService, MedicoMapper medicoMapper) {
        this.medicoService = medicoService;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public CrudBaseService<EntidadeBase<Long>, Long> getService() {
        return (CrudBaseService) this.medicoService;
    }

    @Override
    public BaseMapper<EntidadeBase<Long>, DtoBase> getMapper() {
        return (BaseMapper) this.medicoMapper;
    }


    public ResponseEntity<RespostaPaginada<MedicoDto>> listarMedicos(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 20)
            @ParameterObject Pageable pageable,
            @ParameterObject MedicoDto filtro) {
        return listarPaginado(pageable,this.medicoMapper.toEntity(filtro));
    }






}