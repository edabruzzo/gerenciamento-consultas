package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_medico")
public class Medico extends Pessoa {

    private String crm;

    @Enumerated(EnumType.STRING)
    private EspecialidadeEnum especialidadeEnum;

}
