package br.com.abruzzo.med.voll.dominio.paciente;

import br.com.abruzzo.med.voll.core.service.CrudBaseService;
import br.com.abruzzo.med.voll.dominio.entidades.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService implements CrudBaseService<Paciente,Long> {

    private final PacienteRepository pacienteRepository;

    @Override
    public JpaRepository<Paciente, Long> getRepository() {
        return null;
    }
}
