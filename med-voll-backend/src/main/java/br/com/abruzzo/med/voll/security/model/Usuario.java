package br.com.abruzzo.med.voll.security.model;

import br.com.abruzzo.med.voll.core.model.entities.EntidadeBase;
import br.com.abruzzo.med.voll.security.dto.DadosAutenticacaoDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    @ManyToMany
    @JoinTable(name = "tb_usuario_listaPapeisSistema",
            joinColumns = @JoinColumn(name = "usuario_id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "listaPapeisSistema_id"))
    private Set<PapelSistema> listaPapeisSistema = new LinkedHashSet<>();

    public Usuario(DadosAutenticacaoDto dadosLogin) {
        this.senha = dadosLogin.senha();
        this.login = dadosLogin.login();
    }

    public void atualizar(EntidadeBase dadosParaAlteracao) {
        if(dadosParaAlteracao == null){
            return;
        }
        Usuario dadosAlteracao = (Usuario) dadosParaAlteracao;

        if(dadosAlteracao.getSenha() != null && dadosAlteracao.getSenha() != this.senha){
            this.senha = dadosAlteracao.getSenha();
        }
        if(dadosAlteracao.getLogin() != null && dadosAlteracao.getLogin() != this.login){
            this.login = dadosAlteracao.getLogin();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listaPapeisSistema;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }
}
