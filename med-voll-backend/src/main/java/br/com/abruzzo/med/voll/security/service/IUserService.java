package br.com.abruzzo.med.voll.security.service;

import br.com.abruzzo.med.voll.security.persistence.model.NewLocationToken;
import br.com.abruzzo.med.voll.security.persistence.model.PasswordResetToken;
import br.com.abruzzo.med.voll.security.persistence.model.Usuario;
import br.com.abruzzo.med.voll.security.persistence.model.VerificationToken;
import br.com.abruzzo.med.voll.security.web.dto.UserDto;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    Usuario registerNewUserAccount(UserDto accountDto);

    Usuario getUser(String verificationToken);

    void saveRegisteredUser(Usuario user);

    void deleteUser(Usuario user);

    void createVerificationTokenForUser(Usuario user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(Usuario user, String token);

    Usuario findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    Optional<Usuario> getUserByPasswordResetToken(String token);

    Optional<Usuario> getUserByID(long id);

    void changeUserPassword(Usuario user, String password);

    boolean checkIfValidOldPassword(Usuario user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(Usuario user) throws UnsupportedEncodingException;

    Usuario updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();

    NewLocationToken isNewLoginLocation(String username, String ip);

    String isValidNewLocationToken(String token);

    void addUserLocation(Usuario user, String ip);
}
