package br.com.abruzzo.med.voll.security.persistence.dao;

import br.com.abruzzo.med.voll.security.persistence.model.NewLocationToken;
import br.com.abruzzo.med.voll.security.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);

}
