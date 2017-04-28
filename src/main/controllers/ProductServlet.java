package main.controllers;

import main.models.pojo.Product;
import main.models.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * В данном классе хранятся методы для вызова страницы продукта
 */


public class ProductServlet extends HttpServlet {

    @Autowired
    private ProductServiceInterface productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id")) ;
        Product product = null;

            product = productService.getByID(id);

        req.setAttribute("product", product);

        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);





    }
}
