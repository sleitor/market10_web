package main.controllers.admin;

import main.models.pojo.Order;
import main.models.services.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

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
    private String showListOrders(Model model) {

        model.addAttribute("orders", orderService.getAll());
        return "admin/orderList";
    }

    @RequestMapping(value = "/admin/orderEdit", method = RequestMethod.GET)
    private String editOrders(
            Model model,
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "action") String action
    ) {

        if ("edit".equals(action)){

            model.addAttribute("order",orderService.getByID(id));
            return "admin/orderEdit";
        }


        return "redirect:/admin/orderList";
    }

    @RequestMapping(value = "/admin/orderEdit", method = RequestMethod.POST)
    private String saveOrder(HttpServletRequest req) {

        Order order = new Order(
                Long.parseLong(req.getParameter("uuid")),
                Long.parseLong(req.getParameter("uuid_user")),
                Date.valueOf(req.getParameter("date")),
                Float.parseFloat(req.getParameter("cost")),
                req.getParameter("status")
        );
        orderService.update(order);
        return "redirect:/adnmin/orderList";
    }

}
