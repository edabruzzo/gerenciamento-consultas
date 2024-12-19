package br.com.abruzzo.med.voll.security.security;

import br.com.abruzzo.med.voll.security.persistence.dao.UserRepository;
import br.com.abruzzo.med.voll.security.persistence.model.Privilege;
import br.com.abruzzo.med.voll.security.persistence.model.PapelSistema;
import br.com.abruzzo.med.voll.security.persistence.model.User;
import br.com.abruzzo.med.voll.security.security.dto.DadosAutenticacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    public MyUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked()) {
            throw new RuntimeException("blocked");
        }

        try {
            final User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    // UTIL

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<PapelSistema> PapelSistemas) {
        return getGrantedAuthorities(getPrivileges(PapelSistemas));
    }

    private List<String> getPrivileges(final Collection<PapelSistema> PapelSistemas) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final PapelSistema PapelSistema : PapelSistemas) {
            privileges.add(PapelSistema.getName());
            collection.addAll(PapelSistema.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


    /*
    @jakarta.transaction.Transactional
    public void cadastrarNovoUsuario(DadosAutenticacaoDto dadosLogin) {
        User usuario = new User();
        usuario.setEmail(dadosLogin.login());
        usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
        this.userRepository.save(usuario);
        PapelSistema papel = new PapelSistema();
        papel.setName(dadosLogin.papel().name());
        papel = this.papelRepository.save(papel);
        usuario.getRoles().add(papel);
        this.userRepository.save(usuario);
    }

     */

}
