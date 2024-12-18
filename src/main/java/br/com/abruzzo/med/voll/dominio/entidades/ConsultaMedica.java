package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_consulta_medica")
public class ConsultaMedica implements EntidadeBase<Long> {


    @Id
    @Column(name = "id_consulta_medica", nullable = false)
    private Long id;

    @Column(name = "dt_consulta", nullable = false)
    private LocalDateTime dataConsulta;


}
