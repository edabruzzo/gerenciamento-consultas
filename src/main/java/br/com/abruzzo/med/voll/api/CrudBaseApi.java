package br.com.abruzzo.med.voll.api;

import br.com.abruzzo.med.voll.core.model.Resposta;
import br.com.abruzzo.med.voll.core.model.RespostaPaginada;
import br.com.abruzzo.med.voll.core.model.dto.DtoBase;
import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.core.model.mappers.BaseMapper;
import br.com.abruzzo.med.voll.core.service.CrudBaseService;
import br.com.abruzzo.med.voll.exceptions.RecursoNaoEncontradoException;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CrudBaseApi<E extends EntidadeBase<ID>, D extends DtoBase, ID> extends BaseApi {
    CrudBaseService<EntidadeBase<ID>, ID> getService();

    BaseMapper<EntidadeBase<ID>, DtoBase> getMapper();

    default ResponseEntity<RespostaPaginada<D>> listarPaginado(Pageable pageable, E filtro) {
        RespostaPaginada<EntidadeBase<ID>> resultSearch = this.getService().listarPaginado(pageable, filtro);
        return new ResponseEntity(this.montarRespostaPaginada(resultSearch), HttpStatus.OK);
    }

    default RespostaPaginada<D> montarRespostaPaginada(RespostaPaginada<EntidadeBase<ID>> resultSearch) {
        BaseMapper mapper = this.getMapper();
        Objects.requireNonNull(mapper);
        List<D> dadosDto = mapper.toDto(resultSearch.content());
        RespostaPaginada<D> dadosRetorno = new RespostaPaginada(dadosDto, resultSearch.pageable(), resultSearch.totalPages(), resultSearch.totalElements());
        return dadosRetorno;
    }

    default ResponseEntity<Resposta<DtoBase>> buscar(ID id) throws RecursoNaoEncontradoException {
        EntidadeBase<ID> entidade = this.getService().buscar(id);
        DtoBase dto = this.getMapper().toDto(entidade);
        return this.ok(dto);
    }

    default ResponseEntity<Resposta<DtoBase>> inserir(E dadosParaInclusao) {
        EntidadeBase<ID> entidadeIncluida = this.getService().inserir(dadosParaInclusao);
        DtoBase dtoRetorno = this.getMapper().toDto(entidadeIncluida);
        return this.enviarResposta(dtoRetorno, HttpStatus.CREATED);
    }

    default ResponseEntity<Resposta> atualizar(E dadosParaAlteracao) throws RecursoNaoEncontradoException {
        this.getService().atualizar(dadosParaAlteracao);
        return this.ok();
    }

    default ResponseEntity<Resposta> excluir(ID id) throws RecursoNaoEncontradoException {
        this.getService().excluir(id);
        return this.ok();
    }
}