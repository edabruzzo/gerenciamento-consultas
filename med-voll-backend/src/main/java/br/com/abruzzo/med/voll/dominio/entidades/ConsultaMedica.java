package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.dominio.enums.SituacaoConsultaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_consulta_medica")
public class ConsultaMedica implements EntidadeBase<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta_medica", nullable = false)
    private Long id;

    @Column(name = "dt_consulta", nullable = false)
    private LocalDateTime dataConsulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "in_situacao")
    private SituacaoConsultaEnum situacaoConsultaEnum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_convenio_acionado")
    private Convenio convenioAcionado;

    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if (dadosParaAlteracao == null) {
            return;
        }
        ConsultaMedica dadosAlteracao = (ConsultaMedica) dadosParaAlteracao;
        if (dadosAlteracao.getDataConsulta() != null && !dadosAlteracao.getDataConsulta().equals(this.dataConsulta)) {
            this.dataConsulta = dadosAlteracao.getDataConsulta();
        }
        if (dadosAlteracao.getSituacaoConsultaEnum() != null && !dadosAlteracao.getSituacaoConsultaEnum().equals(this.situacaoConsultaEnum)) {
            this.situacaoConsultaEnum = dadosAlteracao.getSituacaoConsultaEnum();
        }
        if (dadosAlteracao.getMedico() != null) {
            this.medico.atualizar(dadosAlteracao.getMedico());
        }

        if(dadosAlteracao.getConvenioAcionado() != null){
            this.convenioAcionado.atualizar(dadosAlteracao.getConvenioAcionado());
        }
    }
}
