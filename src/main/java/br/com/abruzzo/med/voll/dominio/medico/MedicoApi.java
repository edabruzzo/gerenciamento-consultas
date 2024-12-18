package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.api.CrudBaseApi;
import br.com.abruzzo.med.voll.core.model.Resposta;
import br.com.abruzzo.med.voll.core.model.RespostaPaginada;
import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.core.model.mappers.BaseMapper;
import br.com.abruzzo.med.voll.core.service.CrudBaseService;
import br.com.abruzzo.med.voll.dominio.entidades.Medico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(MedicoApi.API)
public class MedicoApi implements CrudBaseApi<Medico, MedicoDto, Long>{

    public static final String API = "/medicos";
    private final MedicoService medicoService;
    private final MedicoMapper medicoMapper;

    @Override
    public CrudBaseService<EntidadeBase<Long>, Long> getService() {
        return (CrudBaseService) this.medicoService;
    }

    @Override
    public BaseMapper<EntidadeBase<Long>, DtoBase> getMapper() {
        return (BaseMapper) this.medicoMapper;
    }


    @GetMapping
    public ResponseEntity<RespostaPaginada<MedicoDto>> listarMedicos(
            @PageableDefault(sort = "pessoa.nome", direction = Sort.Direction.ASC, page = 0, size = 20)
            @ParameterObject Pageable pageable,
            @ParameterObject MedicoDto filtro) {
        return listarPaginado(pageable,this.medicoMapper.toEntity(filtro));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Resposta<MedicoDto>> cadastrar(@RequestBody @Valid MedicoDto dadosMedico,
                                                         UriComponentsBuilder uriBuilder){
        return this.inserir(this.medicoMapper.toEntity(dadosMedico), API,uriBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid MedicoAtualizarDto dados) {
        Medico medico = this.medicoMapper.dtoAtualizartoEntity(dados);
        medico.setId(id);
        return this.atualizar(medico);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Resposta> remover(@PathVariable Long id){
        return this.excluir(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Resposta<MedicoDto>> obterMedico(@PathVariable Long id){
        return this.buscar(id);
    }

}
