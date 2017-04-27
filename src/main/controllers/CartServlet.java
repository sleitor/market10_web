package main.controllers;

import main.models.pojo.Product;
import main.models.services.ProductService;
import main.models.services.ProductServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * В данном классе хранятся методы для вызова корзины и добавления в нее товаров.
 * Так же здесь происходит обработка и формирование массива заказанных товаров
 */
public class CartServlet extends HttpServlet {

    private static ProductServiceInterface productService = new ProductService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<Long, Integer> cart = new HashMap<>();
        HashMap tenp = (HashMap) req.getSession().getAttribute("cart");
        if (tenp != null) {
            cart = tenp;
        }


        if ("add".equals(req.getParameter("action"))) {

            Long id = Long.parseLong(req.getParameter("id"));
            if (id > 0) {
                if (cart.containsKey(id)) {
                    Integer a = cart.get(id);
                    cart.put(id, ++a);
                } else {
                    cart.put(id, 1);
                }
            }
            req.getSession().setAttribute("cart", cart);
            resp.sendRedirect("/market/catalog");
            return;
        }

        Set<Product> cartProduct = new HashSet<>();


        for (Map.Entry entry : cart.entrySet()) {

            cartProduct.add(productService.getByID( (Long) entry.getKey()));

        }

        req.getSession().setAttribute("cartProduct", cartProduct);
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
