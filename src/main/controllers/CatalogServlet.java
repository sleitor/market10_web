package main.controllers;

import main.models.pojo.Product;
import main.models.services.ProductService;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("products",(Set<Product>) new ProductService().getAll());

        getServletContext().getRequestDispatcher("/catalog.jsp").forward(req,resp);
    }
}