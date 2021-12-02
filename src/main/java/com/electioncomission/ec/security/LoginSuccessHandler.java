package com.electioncomission.ec.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws javax.servlet.ServletException, IOException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		 System.out.print("here ");

        String redirectURL = "/"+userDetails.getRoles().toLowerCase(Locale.ROOT);
//		System.out.println(userDetails.getRoles());

        redirectURL =redirectURL+"/voteEntry";
        System.out.println("redirect Url"+redirectURL);

        try{
            response.sendRedirect(request.getContextPath() + redirectURL);
            super.onAuthenticationSuccess(request, response, authentication);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
