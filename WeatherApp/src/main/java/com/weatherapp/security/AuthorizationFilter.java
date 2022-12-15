package com.weatherapp.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.weatherapp.common.exceptions.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  public AuthorizationFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().contains("/login")
        || request.getServletPath().contains("/register")
        // for development purposes allowing everyone everywhere;
        || request.getServletPath().contains("/")) {
      filterChain.doFilter(request, response);
    } else {
      try {
        if (request.getHeader("token").isEmpty()) {
          throw new InvalidTokenException();
        } else {
          String token = request.getHeader("token");
          DecodedJWT decodedJwt = jwtUtil.verifier.verify(request.getHeader("token"));
          Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
          authorities.add(new SimpleGrantedAuthority(decodedJwt.getClaim("role").toString()));
          SecurityContextHolder.getContext()
              .setAuthentication(
                  new UsernamePasswordAuthenticationToken(
                      decodedJwt.getSubject(), null, authorities));
          if (decodedJwt.getClaim("role").toString().contains("USER")
                  && !request.getServletPath().contains("USER")
              || decodedJwt.getClaim("role").toString().contains("ADMIN")
                  && !request.getServletPath().contains("ADMIN")) {
            throw new InvalidTokenException();
          }
          filterChain.doFilter(request, response);
        }
      } catch (Exception e) {
        filterChain.doFilter(request, response);
        throw new InvalidTokenException();
      }
    }
  }
}
