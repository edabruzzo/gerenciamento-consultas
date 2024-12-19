package br.com.abruzzo.med.voll.security.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.abruzzo.med.voll.security.persistence.dao.PrivilegeRepository;
import br.com.abruzzo.med.voll.security.persistence.dao.PapelSistemaRepository;
import br.com.abruzzo.med.voll.security.persistence.dao.UserRepository;
import br.com.abruzzo.med.voll.security.persistence.model.Privilege;
import br.com.abruzzo.med.voll.security.persistence.model.PapelSistema;
import br.com.abruzzo.med.voll.security.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PapelSistemaRepository papelSistemaRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

        // == create initial PapelSistemas
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege, passwordPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, passwordPrivilege));
        final PapelSistema adminPapelSistema = createPapelSistemaIfNotFound("PapelSistema_ADMIN", adminPrivileges);
        createPapelSistemaIfNotFound("PapelSistema_USER", userPrivileges);

        // == create initial user
        createUserIfNotFound("test@test.com", "Test", "Test", "test", new ArrayList<>(Arrays.asList(adminPapelSistema)));

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public PapelSistema createPapelSistemaIfNotFound(final String name, final Collection<Privilege> privileges) {
        PapelSistema PapelSistema = papelSistemaRepository.findByName(name);
        if (PapelSistema == null) {
            PapelSistema = new PapelSistema(name);
        }
        PapelSistema.setPrivileges(privileges);
        PapelSistema = papelSistemaRepository.save(PapelSistema);
        return PapelSistema;
    }

    @Transactional
    public User createUserIfNotFound(final String email, final String firstName, final String lastName, final String password, final Collection<PapelSistema> PapelSistemas) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }
        user.setRoles(PapelSistemas);
        user = userRepository.save(user);
        return user;
    }

}