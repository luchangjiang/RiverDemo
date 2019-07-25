package com.river.LindSecurity.authentication;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：River
 * @date ：Created in 7/25/2019 5:41 PM
 * @description：
 * @modified By：
 * @version: $
 */
public class LindUserNameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public LindUserNameAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            throw new InternalAuthenticationServiceException("Failed to get the username");
        }

        if (password == null) {
            throw new InternalAuthenticationServiceException("Failed to get the password");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}