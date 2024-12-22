package br.com.abruzzo.med.voll.security.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@Table(name = "tb_user_location")
public class UserLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private boolean enabled;

    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario user;

    public UserLocation() {
        enabled = false;
    }

    public UserLocation(String country, Usuario user) {
        super();
        this.country = country;
        this.user = user;
        enabled = false;
    }

}
