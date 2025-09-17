package main.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectURL = "/";

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();

            if (role.equals("COLABORADOR")) {
                redirectURL = "/actividades";
                break;
            } else if (role.equals("CLIENTE")) {
                redirectURL = "/cliente/home";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}
