package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import br.com.abruzzo.med.voll.dominio.enums.SituacaoMedicoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_medico")
public class Medico implements EntidadeBase<Long> {

    @Id
    private Long id;

    @MapsId
    @JoinColumn(name = "id_pessoa")
    @OneToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(name = "in_especialidade")
    private EspecialidadeEnum especialidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "in_situacao")
    private SituacaoMedicoEnum situacao;

    @OneToMany(mappedBy = "medico", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultas = new ArrayList();

    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if (dadosParaAlteracao == null) {
            return;
        }
        Medico dadosAlteracao = (Medico) dadosParaAlteracao;
        if (dadosAlteracao.getCrm() != null && !dadosAlteracao.getCrm().equals(this.crm)) {
            this.crm = dadosAlteracao.getCrm();
        }
        if (dadosAlteracao.getEspecialidade() != null && !dadosAlteracao.getEspecialidade().equals(this.especialidade)) {
            this.especialidade = dadosAlteracao.getEspecialidade();
        }
        if (dadosAlteracao.getPessoa() != null) {
            this.pessoa.atualizar(dadosAlteracao.getPessoa());
        }
    }
}

