package br.com.abruzzo.med.voll.security.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tb_device_metada")
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String deviceDetails;
    private String location;
    private Date lastLoggedIn;
}
