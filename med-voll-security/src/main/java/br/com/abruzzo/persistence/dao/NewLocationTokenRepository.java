package br.com.abruzzo.persistence.dao;

import br.com.abruzzo.persistence.model.NewLocationToken;
import br.com.abruzzo.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);

}
