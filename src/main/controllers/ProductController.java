package main.controllers;

import main.models.pojo.Product;
import main.models.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Котроллер для страниц товаров
 * В данном классе хранятся методы для вызова страницы продукта
 */
@Controller
public class ProductController {

    private final ProductServiceInterface productService;

    @Autowired
    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

//    @RequestMapping(value = "/product", method = RequestMethod.GET)
//    private String showCurrentProduct() {
//        return "redirect:catalog";
//    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    private String showCurrentProduct(Model model,
                                      @RequestParam(value = "id") Long id) {

        Product product = null;
        product = productService.getByID(id);
        model.addAttribute("product", product);
        return "product";
    }
}
