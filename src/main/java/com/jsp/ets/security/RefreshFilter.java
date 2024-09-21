package com.jsp.ets.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class RefreshFilter extends OncePerRequestFilter {

    private final BaseJwtFilter baseJwtFilter;

    public RefreshFilter(JwtService jwtService) {
        this.baseJwtFilter = new BaseJwtFilter(jwtService) {
        };
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        baseJwtFilter.authenticateFromCookie(request, "rt");
        filterChain.doFilter(request, response);
    }
}
