package br.com.abruzzo.persistence.dao;

import br.com.abruzzo.persistence.model.User;
import br.com.abruzzo.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
