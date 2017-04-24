package main.controllers;

import main.models.ConnectionPool;
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
 * Created by User on 20.04.2017.
 */
public class LoginServlet extends HttpServlet {
    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
        .getResource("log.properties"));
    }

    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    private static UserServiceInterface userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.findUserByLoginAndPassword(login,password);
        if (user != null ) {
            req.getSession().setAttribute("userLogin", login);
            req.getSession().setAttribute("userAdmin", user.isRole());

            logger.debug("user " +login + " logged in");
            if (user.isRole()) {
                resp.sendRedirect(req.getContextPath()+ "/admin/orderList");
            } else {
                resp.sendRedirect(req.getContextPath() + "/cart");
            }

        } else {
            logger.debug("user " +login + " error");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
