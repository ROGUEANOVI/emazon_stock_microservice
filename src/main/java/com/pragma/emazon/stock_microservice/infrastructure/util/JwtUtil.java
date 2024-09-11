package com.pragma.emazon.stock_microservice.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.pragma.emazon.stock_microservice.infrastructure.constant.SecurityMessages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.issuer}")
    private String issuer;

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();

            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(SecurityMessages.INVALID_TOKEN);
        }
    }

    public String extractUsername(DecodedJWT decodedToken) {
        return decodedToken.getSubject();
    }

    public Claim extactSpecifiedClaim(DecodedJWT decodedToken, String claimName) {
        return decodedToken.getClaim(claimName);
    }

    public Map<String, Claim> extractClaims(DecodedJWT decodedToken) {
        return decodedToken.getClaims();
    }
}
