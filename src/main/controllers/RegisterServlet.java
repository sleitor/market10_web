package main.controllers;

import main.models.pojo.User;
import main.models.services.UserService;
import main.models.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * В данном классе хранятся методы для формирования и отправки объекта пользователь
 * на регистрацию и проверка успехности регистрации
 *
 */
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    private static UserServiceInterface userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("Try register user: " + req.getParameter("userName"));
        User user = new User(
                0,
                req.getParameter("userName"),
                req.getParameter("email"),
                req.getParameter("firstName"),
                req.getParameter("secondName"),
                req.getParameter("lastName"),
                req.getParameter("address"),
                req.getParameter("password"),
                false
        );

        if (userService.create(user)){
            logger.debug("Регистрация завершена!");
            resp.sendRedirect(req.getContextPath() + "/cart");
        } else {
            logger.debug("Ошибка регистрации!");
            req.setAttribute("error", "Ошибка регистрации. Такой пользователь уже существует!");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }


    }


}
