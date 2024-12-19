package br.com.abruzzo.med.voll.security.config;

import br.com.abruzzo.med.voll.security.model.PapelSistemaEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private static final String[] WHITE_LIST_URL = new String[]{
            "/login", "/login/cadastrar", "/customError", "/access-denied"
    } ;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                //.httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL)
                        .permitAll()
                        .requestMatchers("/secured")
                        .hasRole(PapelSistemaEnum.ADMIN.name())
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.failureHandler(authenticationFailureHandler())
                        .successHandler(authenticationSuccessHandler()))
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))
                .logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationManager obterAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder obterPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
