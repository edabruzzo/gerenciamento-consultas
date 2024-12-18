package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_medico")
public class Medico extends Pessoa {

    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(name = "in_especialidade")
    private EspecialidadeEnum especialidade;

}
