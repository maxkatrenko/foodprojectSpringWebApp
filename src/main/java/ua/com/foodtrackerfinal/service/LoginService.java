package ua.com.foodtrackerfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginService {

    private LoginService loginService;

    @Autowired
    public LoginService(LoginService loginService) {
        this.loginService = loginService;
    }

}
