package br.com.abruzzo.med.voll.dominio.consultamedica;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idConsultaMedica")
@Table(name = "tb_consulta_medica")
public class ConsultaMedica implements EntidadeBase<Long> {


    @Id
    @Column(name = "id_consulta_medica", nullable = false)
    private Long id;

    private LocalDateTime dataConsulta;

    /*
    private Medico medico;
    private FilialClinica clinica;
    private Paciente paciente;

     */

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}
