package main.controllers.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * В данном классе хранятся методы для вызова и обработки формы редактирования заказов
 */
public class OrderEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/orderEdit.jsp").forward(req, resp);
    }
}
