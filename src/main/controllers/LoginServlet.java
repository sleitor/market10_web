package main.controllers;

import main.models.pojo.User;
import main.models.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * В данном классе хранятся методы для вызова формы логина, записи в сессию факта авторизации,
 * роутинг в зависимости от прав пользователя и запуск процесса авторизации
 */
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Autowired
    private UserServiceInterface userService;
//    = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("logout".equals(req.getParameter("action"))) {
            logger.debug("Выходим из сессии");
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/catalog");
        } else {

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.findUserByLoginAndPassword(login, password);

        if (user != null) {
            req.getSession().setAttribute("userLogin", login);
            req.getSession().setAttribute("userAdmin", user.isRole());

            logger.debug("user " + login + " logged in");
            if (user.isRole()) {
                resp.sendRedirect(req.getContextPath() + "/admin/orderList");
            } else {
                resp.sendRedirect(req.getContextPath() + "/cart");
            }

        } else {
            req.setAttribute("error", "Неверный логин/пароль");
            logger.debug("user " + login + " error");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
