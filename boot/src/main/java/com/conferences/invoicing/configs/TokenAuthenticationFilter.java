package com.conferences.invoicing.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            Authentication authentication = switch (token) {
                case "admin-token" -> new UsernamePasswordAuthenticationToken(
                        "ROLE_ADMIN",
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
                );
                case "user-token" -> new UsernamePasswordAuthenticationToken(
                        "ROLE_USER",
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                );
                default -> null;
            };

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

