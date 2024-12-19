package br.com.abruzzo.med.voll.core.service;

import br.com.abruzzo.med.voll.core.model.RespostaPaginada;
import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.exceptions.MedVollRuntimeException;
import br.com.abruzzo.med.voll.exceptions.MensagemErroEnum;
import br.com.abruzzo.med.voll.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudBaseService<E extends EntidadeBase<ID>, ID>{
    int DEFAULT_PAGE_SIZE = 10;

    JpaRepository<E, ID> getRepository();

    default List<E> listar() {
        return this.getRepository().findAll();
    }

    default RespostaPaginada<E> listarPaginado(Pageable pageable) {
        return this.listarPaginado(pageable,(E) null);
    }

    default RespostaPaginada<E> listarPaginado(Pageable pageable, E filtro) {
        Pageable paginacao = Pageable.ofSize(10);
        if (pageable != null) {
            paginacao = pageable;
        }

        if (filtro == null) {
            return new RespostaPaginada(this.getRepository().findAll(paginacao));
        } else {
            Page<E> result = this.getRepository().findAll(this.configurarFiltroPaginadoIgnorarNulos(filtro), paginacao);
            return new RespostaPaginada(result);
        }
    }

    default Example<E> configurarFiltroPaginado(E filtro) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        return Example.of(filtro, matcher);
    }

    default Example<E> configurarFiltroPaginadoIgnorarNulos(E filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example<E> example = Example.of(filtro, matcher);
        return Example.of(filtro, matcher);
    }

    default E buscar(ID id) throws RecursoNaoEncontradoException {
        return this.getRepository().findById(id).orElseThrow(() -> {
            return new RecursoNaoEncontradoException("Informação de identificador: " + id);
        });
    }

    @Transactional
    default E inserir(E item) {
        return this.salvar(item);
    }

    @Transactional
    default void atualizar(E item) throws RecursoNaoEncontradoException {
        this.buscar(item.getId());
        this.salvar(item);
    }

    @Transactional
    default void excluir(ID id) throws RecursoNaoEncontradoException {
        this.excluir(id, "existem registros vinculados");
    }

    default void excluir(ID id, String mensagemErroVinculos) throws RecursoNaoEncontradoException {
        E entidadeEncontrada = this.buscar(id);

        try {
            this.getRepository().delete(entidadeEncontrada);
        } catch (Exception var5) {
            if (this.getRootCause(var5).getMessage().toLowerCase().contains("violates foreign key")) {
                throw new MedVollRuntimeException(MensagemErroEnum.ERRO_NA_EXCLUSAO_ENTIDADE, new Object[]{mensagemErroVinculos, var5});
            } else {
                throw new MedVollRuntimeException(MensagemErroEnum.ERRO_DESCONHECIDO, var5);
            }
        }
    }

    default Throwable getRootCause(Exception e) {
        if (e.getCause() == null) {
            return e;
        } else {
            Throwable rootCause;
            for(rootCause = e.getCause(); rootCause.getCause() != null && !rootCause.getCause().equals(rootCause); rootCause = rootCause.getCause()) {
            }

            return rootCause;
        }
    }

    default E salvar(E item) {
        try {
            return this.getRepository().save(item);
        } catch (ConstraintViolationException var3) {
            throw var3;
        } catch (PersistenceException var4) {
            if (var4.getCause() instanceof org.hibernate.exception.ConstraintViolationException && var4.getCause().getCause().getMessage().toUpperCase().contains("_UK")) {
                throw new MedVollRuntimeException(MensagemErroEnum.ENTIDADE_JA_CADASTRADA, var4);
            } else {
                throw new MedVollRuntimeException(MensagemErroEnum.ERRO_SALVAR_ENTIDADE, var4);
            }
        } catch (Exception var5) {
            throw new MedVollRuntimeException(MensagemErroEnum.ERRO_DESCONHECIDO, var5);
        }
    }
}