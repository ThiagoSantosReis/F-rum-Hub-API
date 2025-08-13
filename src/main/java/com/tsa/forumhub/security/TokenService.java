package com.tsa.forumhub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.jwt.secret}")
    private String secret;

    @Value("${api.security.jwt.expiration-minutes}")
    private long expirationMinutes;

    @Value("${api.security.jwt.issuer}")
    private String issuer;

    public String gerarToken(String username){
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        Instant data = Instant.now();
        Instant dataExpiracao = data.plus(expirationMinutes, ChronoUnit.MINUTES);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withIssuedAt(Date.from(data))
                .withExpiresAt(Date.from(dataExpiracao))
                .sign(algoritmo);
    }

    public String getSubject(String token){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }

}
