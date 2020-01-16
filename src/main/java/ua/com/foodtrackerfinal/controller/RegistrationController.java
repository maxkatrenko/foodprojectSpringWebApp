package ua.com.foodtrackerfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.foodtrackerfinal.Exception.PasswordsException;
import ua.com.foodtrackerfinal.Exception.UsernameFoundException;
import ua.com.foodtrackerfinal.dto.UserDto;
import ua.com.foodtrackerfinal.entity.User;
import ua.com.foodtrackerfinal.service.RegistrationService;
import ua.com.foodtrackerfinal.service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String getRegistration() {
        System.out.println("REGISTRATION GET"); //TODO delete this line
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(UserDto userDto) throws UsernameFoundException, PasswordsException {
        System.out.println("REGISTRATION POST");//TODO delete this line
        registrationService.registerUser(userDto);
        return "redirect:/login";
    }
}
