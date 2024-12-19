package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_paciente")
public class Paciente implements EntidadeBase<Long> {


    @Id
    private Long id;

    @MapsId
    @JoinColumn(name = "id_paciente")
    @OneToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    private String profissao;

    @OneToMany(mappedBy = "paciente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultas = new ArrayList();

    @ManyToMany
    @JoinTable(name = "tb_paciente_convenios",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "convenios_id_convenio"))
    private Set<Convenio> conveniosPossuidos = new LinkedHashSet<>();

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
