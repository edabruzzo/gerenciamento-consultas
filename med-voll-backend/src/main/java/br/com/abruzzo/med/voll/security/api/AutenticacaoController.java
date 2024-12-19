package br.com.abruzzo.med.voll.security.api;

import br.com.abruzzo.med.voll.security.security.MyUserDetailsService;
import br.com.abruzzo.med.voll.security.security.dto.DadosAutenticacaoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login-alternativa")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final MyUserDetailsService autenticacaoService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDto dadosLogin) {
        UserDetails userDetails = this.autenticacaoService.loadUserByUsername(dadosLogin.login());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            dadosLogin, dadosLogin.senha(), userDetails.getAuthorities()
        );
        Authentication authentication = this.authenticationManager.authenticate(token);
        ResponseEntity resposta = authentication.isAuthenticated()
                ? ResponseEntity.ok().build()
        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return resposta;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosAutenticacaoDto dadosLogin) {
        //this.autenticacaoService.cadastrarNovoUsuario(dadosLogin);
        return ResponseEntity.ok().build();
    }


}
