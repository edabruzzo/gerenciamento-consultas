package br.com.abruzzo.med.voll.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tb_papel_sistema")
public class PapelSistema implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PapelSistemaEnum role;


    @Override
    public String getAuthority() {
        return this.role.name();
    }
}
