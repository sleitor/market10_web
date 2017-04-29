package main.controllers;

import main.models.pojo.Order;
import main.models.pojo.Product;
import main.models.services.OrderServiceInterface;
import main.models.services.ProductServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Контроллер, который обрабалывает работу с корзиной
 */

@Controller
public class CartController {

    Logger logger = Logger.getLogger(CartController.class);

    private final ProductServiceInterface productService;
    private final OrderServiceInterface orderService;

    @Autowired
    public CartController(ProductServiceInterface productService, OrderServiceInterface orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }


    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Model model,
                           HttpServletRequest req,
                           @RequestParam(value = "id", required = false) Long id,
                           @RequestParam(value = "action", required = false) String action) {

        HashMap<Long, Integer> cart = new HashMap<>();
        HashMap<Long, Integer> temp = (HashMap) req.getSession().getAttribute("cart");
        if (temp != null) {
            cart = temp;
        }

        if ("add".equals(action)) {

            if (id > 0) {
                if (cart.containsKey(id)) {
                    Integer a = cart.get(id);
                    cart.put(id, ++a);
                } else {
                    cart.put(id, 1);
                }
            }
            req.getSession().setAttribute("cart", cart);
            req.getSession().setAttribute("add", "Товар добавлен в корзину");
            return "redirect:catalog";
        } else if ("order".equals(action)) {

            Set<Product> cartProduct = (HashSet<Product>) req.getSession().getAttribute("products");

            Order order = new Order(
                    0,
                    0,
                    new Date(System.currentTimeMillis()),
                    0,
                    ""
            );
            orderService.create(order);


//            for (Product item : cartProduct) {
//            }

            return "redirect:cart";
            //req.getSession().setAttribute("cartProduct", cartProduct);


        } else {

            Set<Product> cartProduct = new HashSet<>();
            for (Map.Entry entry : cart.entrySet()) {

                cartProduct.add(productService.getByID((Long) entry.getKey()));
            }
            req.getSession().setAttribute("cartProduct", cartProduct);

        }

        return "cart";
    }

}
