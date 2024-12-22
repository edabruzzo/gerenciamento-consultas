package br.com.abruzzo.med.voll.security.model;

public class ClaimsJWTConstantes {
    // Public Claims (Fonte: https://www.iana.org/assignments/jwt/jwt.xhtml)
    public static final String CLAIM_ISS = "iss"; // Issuer [IESG] [ RFC7519, Section 4.1.1]
    public static final String CLAIM_SUB = "sub"; // Subject [IESG] [ RFC7519, Section 4.1.2]
    public static final String CLAIM_EXP = "exp"; // Expiration Time [IESG] [ RFC7519, Section 4.1.4]
    public static final String CLAIM_IAT = "iat"; // Issued At [IESG] [ RFC7519, Section 4.1.6]
    public static final String CLAIM_JTI = "jti"; // JWT ID [IESG] [ RFC7519, Section 4.1.7]
    public static final String CLAIM_ACT = "act"; // ACT (Actor) [IESG] [RFC 8693, Section 4.1]

    // Custom Claims
    public static final String CLAIM_PAPEL = "papel";
    public static final String CLAIM_NOME = "nome";
    public static final String CLAIM_CPF = "cpf";

}
