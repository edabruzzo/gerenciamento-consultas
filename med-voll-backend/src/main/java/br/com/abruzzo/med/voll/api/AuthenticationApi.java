package br.com.abruzzo.med.voll.api;

import br.com.abruzzo.med.voll.security.security.MyUserDetailsService;
import br.com.abruzzo.med.voll.security.security.dto.DadosAutenticacaoDto;
import br.com.abruzzo.med.voll.security.service.TokenService;
import br.com.abruzzo.med.voll.security.spring.SecSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationApi{


    private final TokenService tokenService;

    private final SecSecurityConfig secSecurityConfig;


    private final MyUserDetailsService myUserDetailsService;

    @PostMapping()
    public ResponseEntity authenticate(@RequestBody DadosAutenticacaoDto dadosAutenticacaoDto){
        UserDetails usuario = this.myUserDetailsService.loadUserByUsername(dadosAutenticacaoDto.login());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                usuario,
                dadosAutenticacaoDto.senha(),
                usuario.getAuthorities()
        );
        usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) this.secSecurityConfig.authProvider().authenticate(usernamePasswordAuthenticationToken);
        return ResponseEntity.ok(this.tokenService.getTokenComIOJsonWebToken(usernamePasswordAuthenticationToken));
    }



}
