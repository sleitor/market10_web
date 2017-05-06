package main.controllers;

import main.models.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер, который обрабатывает обращения к каталогу продуктов
 */
@Controller
public class CatalogController {

    private final ProductServiceInterface productService;

    @Autowired
    public CatalogController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String showCatalog(Model model, HttpServletRequest req) {

        model.addAttribute("products", productService.getAll() );
//        model.addAttribute("add",model.containsAttribute("add"));
        model.addAttribute("add",req.getAttribute("add"));
        return "catalog";
    }

}
