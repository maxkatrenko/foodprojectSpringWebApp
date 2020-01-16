package ua.com.foodtrackerfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.foodtrackerfinal.entity.User;
import ua.com.foodtrackerfinal.service.UserService;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        System.out.println("LOGIN GET"); //TODO delete this line
        return "login";
    }

    @PostMapping("/login")
    public String registerUser(User user, Model model) {
        System.out.println("LOGIN POST"); //TODO delete this line
        return "redirect:home";//TODO i think it doesn't redirect properly
    }
}
