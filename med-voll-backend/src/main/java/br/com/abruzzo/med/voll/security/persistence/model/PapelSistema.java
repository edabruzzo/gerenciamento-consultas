package br.com.abruzzo.med.voll.security.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tb_papel_sistema")
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class PapelSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    private String name;

    public PapelSistema(final String name) {
        super();
        this.name = name;
    }

}