package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.dominio.entidades.Convenio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name="tb_clinica")
public class Clinica implements EntidadeBase<Long> {

    @Id
    @Column(name = "id_clinica")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "tb_clinica_conveniosAtendidos",
            joinColumns = @JoinColumn(name = "clinica_id_clinica"),
            inverseJoinColumns = @JoinColumn(name = "conveniosAtendidos_id_convenio"))
    private Set<Convenio> conveniosAtendidos = new LinkedHashSet<>();

    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if(dadosParaAlteracao == null){
            return;
        }
    }
}
