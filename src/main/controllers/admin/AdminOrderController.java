package main.controllers.admin;

import main.models.services.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для отображения и обработки заказов админом
 */
@Controller
public class AdminOrderController {

    private final OrderServiceInterface orderService;

    @Autowired
    public AdminOrderController(OrderServiceInterface orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/admin/orderList", method = RequestMethod.GET)
    private String showListOrders() {
        return "admin/orderList";
    }

    @RequestMapping(value = "/admin/orderEdit", method = RequestMethod.GET)
    private String editOrders() {
        return "admin/orderEdit";
    }
}
