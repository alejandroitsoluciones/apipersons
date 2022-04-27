package com.demo.security.jwt;

import org.springframework.security.core.Authentication;

import com.demo.security.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;

public interface IJwtProvider {
    String generateToken(UserDetailsImpl auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
