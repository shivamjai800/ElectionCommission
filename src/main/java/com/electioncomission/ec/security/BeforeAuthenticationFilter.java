package com.electioncomission.ec.security;

import com.electioncomission.ec.model.SmsPojo;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public BeforeAuthenticationFilter()
    {
        super.setUsernameParameter("mobileNumber");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException
    {
        System.out.println("request coming here");
        return super.attemptAuthentication(request,response);
    }
}
