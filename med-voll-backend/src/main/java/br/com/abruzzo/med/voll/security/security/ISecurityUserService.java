package br.com.abruzzo.med.voll.security.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
