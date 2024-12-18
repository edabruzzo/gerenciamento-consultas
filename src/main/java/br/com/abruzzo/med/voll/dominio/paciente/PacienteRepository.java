package br.com.abruzzo.med.voll.dominio.paciente;

import br.com.abruzzo.med.voll.dominio.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
