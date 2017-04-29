package main.controllers.admin;

import main.models.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private String showUsers(){

        return "admin/userList";
    }

    @RequestMapping(value = "/admin/userEdit", method = RequestMethod.GET)
    private String editUser(){

        return "admin/userEdit";
    }
}
