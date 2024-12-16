package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medico implements EntidadeBase<Long> {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
