package main.controllers;

import main.models.pojo.User;
import main.models.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.SessionScope;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер, отвечающий за страницу логина
 * В данном классе хранятся методы для вызова формы логина, записи в сессию факта авторизации,
 * роутинг в зависимости от прав пользователя и запуск процесса авторизации
 */

@Controller
public class LoginController {

    private final static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserServiceInterface userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(HttpServletRequest req,
                            @RequestParam(value = "action", required = false) String action){

        if ("logout".equals(action)) {
            logger.debug("Выходим из сессии");
            new SessionScope();
            req.getSession().invalidate();
            return "redirect:catalog";
        } else {
            return "login";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String auth(HttpServletRequest req) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.findUserByLoginAndPassword(login, password);

        if (user != null) {
            req.getSession().setAttribute("userLogin", login);
            req.getSession().setAttribute("userAdmin", user.isRole());

            logger.debug("user " + login + " logged in");
            if (user.isRole()) {
                return "redirect:admin/orderList";
            } else {
                return "redirect:cart";
            }

        } else {
            req.setAttribute("error", "Неверный логин/пароль");
            logger.debug("user " + login + " error");
            return "login";
        }

    }

}
