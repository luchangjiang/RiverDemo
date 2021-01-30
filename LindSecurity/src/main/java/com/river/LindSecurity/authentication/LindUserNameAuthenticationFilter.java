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
<<<<<<< HEAD
        super(new AntPathRequestMatcher("/mobile/token/*", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
=======
        super(new AntPathRequestMatcher("/login", "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            throw new InternalAuthenticationServiceException("Failed to get the username");
        }

        if (password == null) {
            throw new InternalAuthenticationServiceException("Failed to get the password");
        }

<<<<<<< HEAD
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
=======
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}