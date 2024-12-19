package br.com.abruzzo.med.voll.security.persistence.dao;

import br.com.abruzzo.med.voll.security.persistence.model.PapelSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PapelSistemaRepository extends JpaRepository<PapelSistema, Long> {

    PapelSistema findByName(String name);

    @Override
    void delete(PapelSistema role);

}
