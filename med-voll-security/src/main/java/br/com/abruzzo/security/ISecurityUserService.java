package br.com.abruzzo.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
