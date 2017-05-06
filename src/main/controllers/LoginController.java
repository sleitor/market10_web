package main.controllers;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер, отвечающий за страницу логина
 * В данном классе хранятся методы для вызова формы логина, записи в сессию факта авторизации,
 * роутинг в зависимости от прав пользователя и запуск процесса авторизации
 */

@Controller
public class LoginController {

    private final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(HttpServletRequest req) {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
