package main.controllers.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by User on 06.05.2017.
 */
public class AuthentificationHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String path = null;
        Collection<? extends GrantedAuthority> authentications = authentication.getAuthorities();

        for (GrantedAuthority auth :
                authentications) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                path = "/admin/orderList";
                break;
            } else {
                path = "/cart";
            }
        }

        getRedirectStrategy().sendRedirect(request, response, path);
    }
}
