package main.controllers;

import main.models.pojo.Order;
import main.models.pojo.OrderProduct;
import main.models.pojo.Product;
import main.models.pojo.User;
import main.models.services.OrderProductServiceInterface;
import main.models.services.OrderServiceInterface;
import main.models.services.ProductServiceInterface;
import main.models.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Контроллер, который обрабалывает работу с корзиной
 */

@Controller
public class CartController {

    private final OrderProductServiceInterface orderProductService;
    private final ProductServiceInterface productService;
    private final OrderServiceInterface orderService;
    private final UserServiceInterface userService;
    Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    public CartController(OrderProductServiceInterface orderProductService, ProductServiceInterface productService, OrderServiceInterface orderService, UserServiceInterface userService) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }


    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    private String showCart(Model model,
                            HttpServletRequest req,
                            @RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "action", required = false) String action) {


        if ("add".equals(action)) {
            HashMap<Long, Integer> cart = new HashMap<>();
            cart.putAll((HashMap) req.getSession().getAttribute("cart"));

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

            Set<Product> cartProduct = new HashSet<>();
            for (Map.Entry entry : cart.entrySet()) {
                Product product = productService.getByID((Long) entry.getKey());
                Integer quantityOfCart = (Integer) entry.getValue();
                if (product.getQuantity() < quantityOfCart) {
                    entry.setValue(--quantityOfCart);
                    req.getSession().setAttribute("add", "Ошибка!!! Нет такого числа товаров в наличии!");
                }
                cartProduct.add(product);

            }
            req.getSession().setAttribute("cartProduct", cartProduct);

            return "redirect:/catalog";

        }


        return "cart";
    }

    @RequestMapping(value = "/cart/remove")
    private String removeFromCart(HttpServletRequest req,
                                  @RequestParam(value = "id") Long id) {

        HashMap<Long, Integer> cart = new HashMap<>();
        cart.putAll((HashMap) req.getSession().getAttribute("cart"));
        if (cart.containsKey(id)) {
            cart.remove(id);
            req.getSession().setAttribute("cart", cart);
            req.getSession().setAttribute("add", "Товар удален из корзины");

            Set<Product> cartProduct = (HashSet) req.getSession().getAttribute("cartProduct");
            Product product = productService.getByID(id);
            boolean t = cartProduct.remove(product);
            System.out.println(t);
            req.getSession().setAttribute("cartProduct", cartProduct);

        } else {
            req.getSession().setAttribute("add", "Нет такого товара в корзине!");
        }
        if (cart.size() == 0) {
            return "redirect:/catalog";
        }

        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/order")
    private String makeOrder(HttpServletRequest req) {

        HashMap<Long, Integer> cart = new HashMap<>();
        cart.putAll((HashMap<Long, Integer>) req.getSession().getAttribute("cart"));
        Set<Product> cartProduct = (HashSet<Product>) req.getSession().getAttribute("cartProduct");
        String sr = req.getUserPrincipal().getName();
        User user = userService.findUserbyLogin(req.getUserPrincipal().getName());
        Float cost = 0f;
        Order order = new Order(
                0,
                user.getUuid(),
                new Date(System.currentTimeMillis()),
                cost,
                "Новый"
        );
        int orderNum = orderService.create(order);
        for (Product item : cartProduct) {
            cost += item.getCost() * cart.get(item.getUuid());
            OrderProduct orderProduct = new OrderProduct(
                    0,
                    orderNum,
                    item.getUuid(),
                    cart.get(item.getUuid()),
                    item.getCost()
            );
            orderProductService.create(orderProduct);
        }
        order.setCost(cost);
        order.setUuid(orderNum);
        orderService.update(order);
        req.getSession().setAttribute("cart", new HashMap<Long, Integer>());
        req.getSession().setAttribute("orderProducts", new HashSet<OrderProduct>());
        req.getSession().setAttribute("add", "Создан новый заказ. Спасибо!");
        return "redirect:/catalog";

    }

    @RequestMapping(value = "/cart/clear")
    private String clearCart(HttpServletRequest request) {
        request.getSession().setAttribute("cart", new HashMap<Long, Integer>());
        request.getSession().setAttribute("add", "Корзина очищена");
        return "redirect:/catalog";
    }

}
