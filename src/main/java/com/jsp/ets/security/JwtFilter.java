package com.jsp.ets.security;

import com.jsp.ets.user.UserRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService  jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = Optional.ofNullable(request.getCookies())
                .stream()
                .flatMap(Arrays::stream)
                .filter(cookie -> "at".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if (token != null && !token.isEmpty()) {
            Claims claims = jwtService.parseJwt(token);
            String email = claims.get("email", String.class);
            String role = claims.get("role", String.class);

            if (email != null && role != null) {
                UserRole userRole = UserRole.valueOf(role);

                List<SimpleGrantedAuthority> authorities = userRole.getPrivileges().stream()
                        .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                        .toList();

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
