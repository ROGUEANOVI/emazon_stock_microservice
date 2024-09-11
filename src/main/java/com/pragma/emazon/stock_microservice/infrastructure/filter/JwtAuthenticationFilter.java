package com.pragma.emazon.stock_microservice.infrastructure.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon.stock_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.stock_microservice.infrastructure.constant.SecurityMessages;
import com.pragma.emazon.stock_microservice.infrastructure.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith(SecurityMessages.BEARER)) {
            jwtToken = jwtToken.substring(7);

            try {
                DecodedJWT decodedToken = jwtUtil.validateToken(jwtToken);

                String username = jwtUtil.extractUsername(decodedToken);

                Date expiresAt = decodedToken.getExpiresAt();
                if (expiresAt.before(new Date())) {
                    throw new JWTVerificationException(SecurityMessages.TOKEN_EXPIRED);
                }

                String authoritiesList = jwtUtil.extactSpecifiedClaim(decodedToken, SecurityMessages.AUTHORITIES).asString();

                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesList);

                SecurityContext securityContext = SecurityContextHolder.getContext();

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

                securityContext.setAuthentication(authentication);

                SecurityContextHolder.setContext(securityContext);
            }
            catch (JWTVerificationException ex) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                new ObjectMapper().writeValue(response.getOutputStream(), Map.of(GlobalMessages.MESSAGE, ex.getMessage()));
            }
        }

        filterChain.doFilter(request, response);
    }
}
