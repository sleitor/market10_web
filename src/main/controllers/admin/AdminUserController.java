package main.controllers.admin;

import main.models.pojo.User;
import main.models.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер для отображения и обработки пользователей админом
 */
@Controller
public class AdminUserController {

    private final UserServiceInterface userService;

    @Autowired
    public AdminUserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
    private String showUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/userList";
    }

    @RequestMapping(value = "/admin/userEdit", method = RequestMethod.GET)
    private String editUser(Model model,
                            @RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "action") String action) {

        if ("add".equals(action)) {
            model.addAttribute("id", 0);
            return "admin/userEdit";
        }
        if ("edit".equals(action)) {
            model.addAttribute("user", userService.getByID(id));
            return "admin/userEdit";
        }
        if ("delete".equals(action)) {
            userService.deleteByID(id);
            return "redirect:/admin/userList";
        }

        if("access".equals(action)){
            userService.modifyAccess(id);
            return "redirect:/admin/userList";
        }

        return "redirect:/admin/userList";
    }

    @RequestMapping(value = "/admin/userEdit", method = RequestMethod.POST)
    private String saveForm(
            HttpServletRequest req
    ) {
        User user = new User(
                Long.parseLong(req.getParameter("uuid")),
                req.getParameter("userName"),
                req.getParameter("email"),
                req.getParameter("firstName"),
                req.getParameter("secondName"),
                req.getParameter("lastName"),
                req.getParameter("address"),
                req.getParameter("password"),
                Boolean.parseBoolean(req.getParameter("role"))
        );
        if (user.getUuid()==0){
            userService.create(user);
        } else {
            userService.update(user);
        }


        return "redirect:/admin/userList";
    }
}
