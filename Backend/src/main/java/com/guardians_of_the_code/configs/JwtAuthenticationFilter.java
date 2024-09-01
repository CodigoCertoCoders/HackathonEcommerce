package com.guardians_of_the_code.configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.guardians_of_the_code.services.GoogleJwtKeyProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private GoogleJwtKeyProvider googleJwtKeyProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if ("/login".equals(requestURI) || ("/clients".equals(requestURI) && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = findToken(request);

        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("\"Token não encontrado ou mal formatado\"");
            return;
        }

        try {
            JWTVerifier verifier = JWT.require(Algorithm.RSA256(googleJwtKeyProvider))
                    .build();

            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("\"Token inválido\"");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String findToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Authorization Bearer ")) {
            return null;
        }

        String token = authorization.substring(21).trim();

        return token;
    }
}