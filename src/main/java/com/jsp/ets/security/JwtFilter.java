package com.jsp.ets.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final BaseJwtFilter baseJwtFilter;

    public JwtFilter(JwtService jwtService) {
        this.baseJwtFilter = new BaseJwtFilter(jwtService) {
        };
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {
        baseJwtFilter.authenticateFromCookie(request, "at");
        filterChain.doFilter(request, response);
    }

}
