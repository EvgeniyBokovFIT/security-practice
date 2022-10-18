package com.evgemba.securitypractice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author Evgeniy
 * @since 19.10.2022
 */
public class JWTDecoder {
    public static DecodedJWT getDecodedJWTFromHttpAuthHeader(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        String secretKey = "secret";
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
