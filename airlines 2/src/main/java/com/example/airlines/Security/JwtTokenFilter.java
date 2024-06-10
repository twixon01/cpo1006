package com.example.airlines.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = jwtTokenProvider.resolveAccessToken((HttpServletRequest) request);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuth(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch (JwtAuthException exc){
            String errorMessage = "JWT TOKEN IS expired or invalid";
            exc = new JwtAuthException(errorMessage, HttpStatus.UNAUTHORIZED);

            SecurityContextHolder.clearContext();

            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(exc.getHttpStatus().value(), errorMessage);

            throw exc;
        }

        chain.doFilter(request, response);

    }
}
