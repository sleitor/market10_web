package main.controllers;

import main.models.pojo.Order;
import main.models.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created by User on 21.05.2017.
 */
@Controller
public class HistoryController {

    private final OrderService orderService;

    @Autowired
    public HistoryController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    private String showHistoryOrders(HttpServletRequest request,
                                     Authentication authentication) {
        List<Order> orders = orderService.getAll();

        String user = request.getUserPrincipal().getName();
        orders.removeIf(order -> !Objects.equals(order.getLogin_user(), user));

        request.setAttribute("orders", orders);
        return "history";
    }
}
