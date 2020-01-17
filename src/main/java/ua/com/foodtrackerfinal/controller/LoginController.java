package ua.com.foodtrackerfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.foodtrackerfinal.dto.UserDto;
import ua.com.foodtrackerfinal.service.AuthorizationService;
import ua.com.foodtrackerfinal.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    private UserService userService;
    private AuthorizationService authorizationService;

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
//    @PreAuthorize("hasRole('USER')")
    public String loginUser(@Valid @RequestBody UserDto userDto) {
        System.out.println("LOGIN POST"); //TODO delete this line
        authorizationService.loginUser(userDto);
        return "redirect:/home";//TODO i think it doesn't redirect properly
    }
}
