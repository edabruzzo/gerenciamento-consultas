package br.com.abruzzo.med.voll.security.api;

import br.com.abruzzo.med.voll.api.BaseApi;
import br.com.abruzzo.med.voll.core.model.Resposta;
import br.com.abruzzo.med.voll.exceptions.MensagemErroEnum;
import br.com.abruzzo.med.voll.security.dto.DadosAutenticacaoDto;
import br.com.abruzzo.med.voll.security.service.AutenticacaoService;
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
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController implements BaseApi {

    private final AutenticacaoService autenticacaoService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDto dadosLogin) {
        UserDetails userDetails = this.autenticacaoService.loadUserByUsername(dadosLogin.login());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            dadosLogin, dadosLogin.senha(), userDetails.getAuthorities()
        );
        Authentication authentication = this.authenticationManager.authenticate(token);
        ResponseEntity resposta = authentication.isAuthenticated()
                ? ok()
        : this.enviarResposta(new Resposta<String>(
                String.format(MensagemErroEnum.ERRO_AUTENTICACAO_USUARIO.getMensagem(),dadosLogin.login()))
                , HttpStatus.UNAUTHORIZED);
        return resposta;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosAutenticacaoDto dadosLogin) {
        this.autenticacaoService.cadastrarNovoUsuario(dadosLogin);
        return ok();
    }


}
