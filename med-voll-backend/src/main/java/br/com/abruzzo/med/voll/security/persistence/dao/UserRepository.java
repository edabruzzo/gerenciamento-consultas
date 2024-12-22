package br.com.abruzzo.med.voll.security.persistence.dao;

import br.com.abruzzo.med.voll.security.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    @Override
    void delete(Usuario user);

}
