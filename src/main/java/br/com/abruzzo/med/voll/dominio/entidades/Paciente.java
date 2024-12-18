package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_paciente")
public class Paciente extends Pessoa {

    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(name = "in_especialidade")
    private EspecialidadeEnum especialidade;

}
