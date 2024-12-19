package br.com.abruzzo.med.voll.security.service;

import br.com.abruzzo.med.voll.security.dto.DadosAutenticacaoDto;
import br.com.abruzzo.med.voll.security.model.PapelSistema;
import br.com.abruzzo.med.voll.security.model.Usuario;
import br.com.abruzzo.med.voll.security.repository.PapelSistemaRepository;
import br.com.abruzzo.med.voll.security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PapelSistemaRepository papelRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setLogin(username);
        return this.repository.findOne(Example.of(usuario))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void cadastrarNovoUsuario(DadosAutenticacaoDto dadosLogin) {
        Usuario usuario = new Usuario(dadosLogin);
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
        this.repository.save(usuario);
        PapelSistema papel = new PapelSistema();
        papel.setRole(dadosLogin.papel());
        papel = this.papelRepository.save(papel);
        usuario.getListaPapeisSistema().add(papel);
        this.repository.save(usuario);
    }
}
