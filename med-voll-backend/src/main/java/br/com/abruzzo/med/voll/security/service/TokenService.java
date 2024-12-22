package br.com.abruzzo.med.voll.security.service;

import br.com.abruzzo.med.voll.security.exceptions.ErroCriacaoTokenJWT;
import br.com.abruzzo.med.voll.security.exceptions.enums.MensagemErroEnum;
import br.com.abruzzo.med.voll.security.model.ClaimsJWTConstantes;
import br.com.abruzzo.med.voll.security.persistence.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {

    private static final long EXPIRACAO_MILLISEGUNDOS = 60000;
    @Value("${med-voll.jwt.secret}")
    private String secret;
    public String getTokenComAuth0(UsernamePasswordAuthenticationToken usuario) {
        Usuario principal = (Usuario) usuario.getPrincipal();
        try{
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(principal.getId().toString())
                    .withClaim(ClaimsJWTConstantes.CLAIM_PAPEL, principal.getRoles().stream().toList().get(0).getName())
                    .withClaim(ClaimsJWTConstantes.CLAIM_NOME, usuario.getName())
                    .withExpiresAt(getExpiracao())
                    .sign(Algorithm.HMAC256(this.secret)).toString();
        }catch (JWTCreationException e){
            throw new ErroCriacaoTokenJWT(e, MensagemErroEnum.ERRO_CRIACAO_TOKEN.getDescricao());
        }
    }

    public String getTokenComIOJsonWebToken(UsernamePasswordAuthenticationToken usuario) {
        Usuario principal = (Usuario) usuario.getPrincipal();
        try{
            return Jwts.builder()
                    .setIssuer("io.jsonwebtoken")
                    .setSubject(principal.getEmail())
                    .claim(ClaimsJWTConstantes.CLAIM_PAPEL, principal.getRoles().stream().toList().get(0).getName().replace("ROLE_",""))
                    .claim(ClaimsJWTConstantes.CLAIM_NOME, usuario.getName())
                    .setExpiration(getExpiracaoDate())
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }catch (RuntimeException e){
            throw new ErroCriacaoTokenJWT(e, MensagemErroEnum.ERRO_CRIACAO_TOKEN.getDescricao());
        }
    }

    private Date getExpiracaoDate() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 24);
    }

    private Instant getExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    private boolean isTokenExpirado(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isTokenValido(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpirado(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        // Generate a secure key for HMAC-SHA256
        //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
