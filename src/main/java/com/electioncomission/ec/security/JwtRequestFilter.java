package com.electioncomission.ec.security;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

    @Component
    public class JwtRequestFilter extends OncePerRequestFilter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {
            Cookie[] cookie = request.getCookies();
//            System.out.println("cookie = "+cookie);
//            System.out.println("session = "+request.getSession().getAttribute("Authorization"));
            String temp="";
            for(int i=0;cookie!=null && i<cookie.length;i++)
            {
//                System.out.println(cookie[i].getName()+cookie[i].getValue());
                if(cookie[i].getName().equals("Authorization"))
                {
                    temp = "Bearer "+cookie[i].getValue();
//                    System.out.println("yes = "+cookie[i].getValue());
                }
;            }
            final String requestTokenHeader = temp;

            String mobileNumber = null;
            String jwtToken = null;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
//            System.out.println(requestTokenHeader);
//            System.out.println(request.toString());
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    mobileNumber = jwtTokenUtil.getMobileNumberFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

            // Once we get the token validate it.
            if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(mobileNumber);

                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            chain.doFilter(request, response);
        }

    }
