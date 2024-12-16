package br.com.abruzzo.med.voll.core.model;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public record RespostaPaginada<T>(List<T> content, Paginacao pageable, int totalPages, long totalElements) {
    public RespostaPaginada(Page<T> result) {
        this(result.getContent(), new Paginacao(result.getPageable()), result.getTotalPages(), result.getTotalElements());
    }

    public RespostaPaginada(List<T> content, Paginacao pageable, int totalPages, long totalElements) {
        this.content = content;
        this.pageable = pageable;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> content() {
        return this.content;
    }

    public Paginacao pageable() {
        return this.pageable;
    }

    public int totalPages() {
        return this.totalPages;
    }

    public long totalElements() {
        return this.totalElements;
    }

    public static record Paginacao(int pageNumber, int pageSize, long offset) {
        public Paginacao(Pageable paginacao) {
            this(paginacao.getPageNumber(), paginacao.getPageSize(), paginacao.getOffset());
        }

        public Paginacao(int pageNumber, int pageSize, long offset) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.offset = offset;
        }

        public int pageNumber() {
            return this.pageNumber;
        }

        public int pageSize() {
            return this.pageSize;
        }

        public long offset() {
            return this.offset;
        }
    }
}
