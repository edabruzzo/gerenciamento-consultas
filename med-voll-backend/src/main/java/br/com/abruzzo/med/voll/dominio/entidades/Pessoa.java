package br.com.abruzzo.med.voll.dominio.entidades;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.security.persistence.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_pessoa",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"cpf","nome"})})
public class Pessoa implements EntidadeBase<Long>{

    @Id
    private Long id;
    private String nome;

    @Email
    private String email;

    @MapsId
    @JoinColumn(name = "id_pessoa")
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Usuario usuario;

    @CPF
    @Column(unique = true, nullable = false)
    private String cpf;
    @Embedded
    protected Endereco endereco;

    private LocalDate dataNascimento;

    @Override
    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if (dadosParaAlteracao == null) {
            return;
        }
        Pessoa dadosAlteracao = (Pessoa) dadosParaAlteracao;

        // Atualiza o nome se necessário
        if (dadosAlteracao.getNome() != null && !dadosAlteracao.getNome().equals(this.nome)) {
            this.nome = dadosAlteracao.getNome();
        }

        // Atualiza o email se necessário
        if (dadosAlteracao.getEmail() != null && !dadosAlteracao.getEmail().equals(this.email)) {
            this.email = dadosAlteracao.getEmail();
        }

        // Atualiza o CPF se necessário
        if (dadosAlteracao.getCpf() != null && !dadosAlteracao.getCpf().equals(this.cpf)) {
            this.cpf = dadosAlteracao.getCpf();
        }

        // Atualiza a data de nascimento, se necessário
        if (dadosAlteracao.getDataNascimento() != null && !dadosAlteracao.getDataNascimento().equals(this.dataNascimento)) {
            this.dataNascimento = dadosAlteracao.getDataNascimento();
        }

        // Atualiza o endereço
        if (dadosAlteracao.getEndereco() != null) {
            if(this.endereco == null){
                this.endereco = new Endereco();
            }
            this.endereco.atualizar(dadosAlteracao.getEndereco());
        }
    }
}
