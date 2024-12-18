package br.com.abruzzo.med.voll.dominio.medico;

import br.com.abruzzo.med.voll.core.service.CrudBaseService;
import br.com.abruzzo.med.voll.dominio.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public class MedicoService implements CrudBaseService<Medico, Long> {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public JpaRepository<Medico, Long> getRepository() {
        return this.medicoRepository;
    }
}
