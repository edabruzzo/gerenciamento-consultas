package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.dominio.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
