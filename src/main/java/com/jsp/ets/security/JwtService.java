package com.jsp.ets.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${my_app.jwt.secret}")
    private String secret;

    @Value("${my_app.jwt.access_expiry}")
    private long access_expiry;

    public String createJwt(String email, String userId, String role){
       return Jwts.builder()
                .setClaims(Map.of("userId",userId,"email",email,"role",role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(access_expiry*60*1000)))
                        .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
