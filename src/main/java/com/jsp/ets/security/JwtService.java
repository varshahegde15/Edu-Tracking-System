package com.jsp.ets.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${my_app.jwt.secret}")
    private String secret;
}
