package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name="tb_convenio")
public class Convenio implements EntidadeBase<Long> {

    @Id
    @Column(name = "id_convenio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeConvenio;

    private String cnpj;

    private LocalDateTime dataExpiracaoContratoClinica;

    private Long numeroContratoClinica;

    @OneToMany(mappedBy = "convenioAcionado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ConsultaMedica> consultaMedicasRealizadas = new ArrayList<>();


    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if(dadosParaAlteracao == null){
            return;
        }
        Convenio dadosAlteracao = (Convenio) dadosParaAlteracao;

        if(dadosAlteracao.getCnpj() != null && this.cnpj != dadosAlteracao.getCnpj()){
            this.cnpj = dadosAlteracao.getCnpj();
        }

        if(dadosAlteracao.nomeConvenio != null && this.nomeConvenio != dadosAlteracao.getNomeConvenio()){
            this.nomeConvenio = dadosAlteracao.getNomeConvenio();
        }

        if(dadosAlteracao.dataExpiracaoContratoClinica != null && this.dataExpiracaoContratoClinica != dadosAlteracao.getDataExpiracaoContratoClinica()){
            this.dataExpiracaoContratoClinica = dadosAlteracao.getDataExpiracaoContratoClinica();
        }

        if(dadosAlteracao.numeroContratoClinica != null && this.numeroContratoClinica != dadosAlteracao.getNumeroContratoClinica()){
            this.numeroContratoClinica = dadosAlteracao.numeroContratoClinica;
        }



    }
}
