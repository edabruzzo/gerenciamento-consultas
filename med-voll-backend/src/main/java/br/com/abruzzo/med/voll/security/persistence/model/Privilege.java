package br.com.abruzzo.med.voll.security.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tb_privilegio")
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<PapelSistema> roles;

    public Privilege(final String name) {
        super();
        this.name = name;
    }
}
