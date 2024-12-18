package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.dominio.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_paciente")
public class Paciente implements EntidadeBase<Long> {


    @Id
    private Long id;

    @MapsId
    @JoinColumn(name = "id_pessoa")
    @OneToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    private String profissao;

    @OneToMany(mappedBy = "paciente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultas = new ArrayList();

    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if (dadosParaAlteracao == null) {
            return;
        }
        Paciente dadosAlteracao = (Paciente) dadosParaAlteracao;
        if (dadosAlteracao.getProfissao() != null && !dadosAlteracao.getProfissao().equals(this.profissao)) {
            this.profissao = dadosAlteracao.getProfissao();
        }
        if (dadosAlteracao.getPessoa() != null) {
            this.pessoa.atualizar(dadosAlteracao.getPessoa());
        }
    }

}
