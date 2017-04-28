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
import java.util.Set;

/**
 * В данном классе хранятся методы для вызова каталога
 */

public class CatalogServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Autowired
    private ProductServiceInterface productService;

//    private static ProductServiceInterface productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setAttribute("products",(Set<Product>) new ProductService().getAll());


        req.setAttribute("products", (Set<Product>) productService.getAll());


        getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }
}