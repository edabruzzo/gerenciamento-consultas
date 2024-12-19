package br.com.abruzzo.med.voll.security.captcha;

import br.com.abruzzo.med.voll.security.web.error.ReCaptchaInvalidException;

public interface ICaptchaService {
    
    default void processResponse(final String response) throws ReCaptchaInvalidException {}
    
    default void processResponse(final String response, String action) throws ReCaptchaInvalidException {}
    
    String getReCaptchaSite();

    String getReCaptchaSecret();
}
