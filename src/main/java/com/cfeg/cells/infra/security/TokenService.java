package com.cfeg.cells.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cfeg.cells.models.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(User user) {
        try { Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create().withIssuer("CFEG")
                    .withSubject(user.getUsername())
                    .withClaim("role", user.getRole().name())
                    .withClaim("id", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error to create JWT", exception);
        }
    }

    public String getNameToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null or empty");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("CFEG")
                    .build()
                    .verify(token);
            String subject = decodedJWT.getSubject();

            if (subject == null || subject.isEmpty()) {
                throw new RuntimeException("Invalid subject");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            System.err.println("Error validation the token JWT: " + exception.getMessage());
            throw new RuntimeException("Inválid token or failed verification");
        }
    }

    public String getRoleFromToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null or empty");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("CFEG")
                    .build()
                    .verify(token);

            String role = decodedJWT.getClaim("role").asString();
            if (role == null || role.isEmpty()) {
                throw new RuntimeException("The role is invalid");
            }
            return role;
        } catch (JWTVerificationException exception) {
            System.err.println("Error validation the token JWT: " + exception.getMessage());
            throw new RuntimeException("Inválid token or failed verification");
        }
    }
}
