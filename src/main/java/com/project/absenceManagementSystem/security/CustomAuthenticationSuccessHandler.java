package com.project.absenceManagementSystem.security;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_STUDENT")) {
            redirectStrategy.sendRedirect(request, response, "/student");
        } else if (roles.contains("ROLE_TEACHER")) {
            redirectStrategy.sendRedirect(request, response, "/teacher");
        } else if (roles.contains("ROLE_CADRE_ADMINISTRATOR")) {
            redirectStrategy.sendRedirect(request, response, "/cadre-administrator");
        } else if (roles.contains("ROLE_ADMINISTRATOR")) {
            redirectStrategy.sendRedirect(request, response, "/administrator");
        } else {
            throw new IllegalStateException();
        }
    }
}

