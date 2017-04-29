package main.controllers.admin;

import main.models.pojo.Product;
import main.models.services.ProductServiceInterface;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер для отображения и обработки продуктов админом
 */

@Controller
public class AdminProductController {

    private final ProductServiceInterface productService;

    @Autowired
    public AdminProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/admin/productList", method = RequestMethod.GET)
    private String showProduct(Model model) {

        model.addAttribute("products", productService.getAll());
        return "admin/productList";
    }

    @RequestMapping(value = "/admin/productEdit", method = RequestMethod.GET)
    private String editProduct(
            Model model,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "action") String action
    ) {

        if ("delete".equals(action) && (id > 0)) {
            productService.deleteByID(id);
            return "redirect:/admin/productList";
        }

        if ("add".equals(action)) {
            model.addAttribute("id", 0);
            return "admin/productEdit";
        }

        if ("edit".equals(action) && (id > 0)) {
            model.addAttribute("product", productService.getByID(id));
            return "admin/productEdit";
        }

        return "redirect:admin/productList";
    }

    @RequestMapping(value = "/admin/productEdit", method = RequestMethod.POST)
    private String saveProduct(HttpServletRequest req) {

        Product product = new Product(
                Long.parseLong(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("quantity")),
                Float.parseFloat(req.getParameter("cost"))
        );

        if (product.getUuid() == 0) {
            productService.create(product);
        } else {
            productService.update(product);
        }
        return "redirect:/admin/productList";
    }
}
