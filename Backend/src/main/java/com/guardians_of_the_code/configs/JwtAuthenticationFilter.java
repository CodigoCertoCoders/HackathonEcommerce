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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println(requestURI);

        if (
                ("/clients".equals(requestURI) && "POST".equals(httpRequest.getMethod())) ||
                "/login/verifyCredentials".equals(requestURI) ||
                "/products".equals(requestURI) ||
                requestURI.matches("/products/[^/]+") ||
                        "/static/**".equals(requestURI) ||
                        requestURI.startsWith("/swagger-ui/") ||
                        "/swagger-ui/index.html".equals(requestURI) ||
                        requestURI.startsWith("/v3/api-docs")
        ) {
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
            DecodedJWT decodedJWT = JWT.decode(token);
            String algorithm = decodedJWT.getHeaderClaim("alg").asString();

            JWTVerifier verifier;

            if ("RS256".equals(algorithm)) {
                verifier = JWT.require(Algorithm.RSA256(googleJwtKeyProvider))
                        .build();
            } else if ("HS256".equals(algorithm)) {
                verifier = JWT.require(Algorithm.HMAC256("GuardioesDoCodigoMaltex"))
                        .build();
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("\"Algoritmo não suportado\"");
                return;
            }
            verifier.verify(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("\"Token inválido\"");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String findToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");


        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }

        return authorization.substring(7).trim();
    }
}