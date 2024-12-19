package br.com.abruzzo.med.voll.security.repository;

import br.com.abruzzo.med.voll.security.model.PapelSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PapelSistemaRepository extends JpaRepository<PapelSistema, Long> {
}
