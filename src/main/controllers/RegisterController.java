package main.controllers;

import main.models.pojo.User;
import main.models.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Контроллер, отвечающий за регистрацию покупателей в системе, и пренаправление
 * их на разные страницы в зависимости от тогго, что есть в сессии
 */
@Controller
public class RegisterController {

    private static Logger logger = Logger.getLogger(RegisterController.class);

    private final UserServiceInterface userService;

    @Autowired
    public RegisterController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private String formRegister() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    private String doRegister(
            HttpServletRequest req,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "secondName") String secondName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "password") String password) {

        logger.debug("Try register user: " + userName);
        User user = new User(
                0,
                userName,
                email,
                firstName,
                secondName,
                lastName,
                address,
                password,
                false
        );

        if (userService.create(user)>0) {
            logger.debug("Регистрация завершена!");

            logger.debug("Для удобства, сразу авторизуем пользователя");
            req.getSession().setAttribute("userLogin", userName);
            req.getSession().setAttribute("userAdmin", user.isRole());

            HashMap cart = new HashMap<>();
            HashMap temp = (HashMap) req.getSession().getAttribute("cart");
            if (temp != null) {
                cart = temp;
            }

            if (cart.size() > 0) {
                return "redirect:cart";
            } else {
                return "redirect:catalog";
            }

        } else {
            logger.debug("Ошибка регистрации!");
            req.setAttribute("error", "Ошибка регистрации. Такой пользователь уже существует!");
            return "registration";
        }

    }
}
