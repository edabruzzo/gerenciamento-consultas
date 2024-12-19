package br.com.abruzzo.med.voll.security.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tb_new_location_token")
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class NewLocationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserLocation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_location_id")
    private UserLocation userLocation;

    public NewLocationToken(final String token) {
        super();
        this.token = token;
    }

    public NewLocationToken(final String token, final UserLocation userLocation) {
        super();
        this.token = token;
        this.userLocation = userLocation;
    }

}
