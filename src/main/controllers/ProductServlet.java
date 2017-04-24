package main.controllers;

import main.models.DAO.ProductInterface;
import main.models.pojo.Product;
import main.models.services.ProductService;
import main.models.services.ProductServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 23.04.2017.
 */
public class ProductServlet extends HttpServlet {
    private static ProductServiceInterface productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id")) ;
        Product product = productService.getByID(id);

        req.setAttribute("product", product);

        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);





    }
}
