package br.com.abruzzo.med.voll.security.persistence.dao;

import br.com.abruzzo.med.voll.security.persistence.model.Usuario;
import br.com.abruzzo.med.voll.security.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, Usuario user);

}
