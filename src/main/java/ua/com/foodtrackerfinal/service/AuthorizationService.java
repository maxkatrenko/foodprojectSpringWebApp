package ua.com.foodtrackerfinal.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import ua.com.foodtrackerfinal.Exception.PasswordsDontMatchException;
import ua.com.foodtrackerfinal.dto.UserDto;
import ua.com.foodtrackerfinal.entity.User;
import ua.com.foodtrackerfinal.repository.UserRepository;

import javax.security.auth.login.AccountNotFoundException;

@Controller
public class AuthorizationService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthorizationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @SneakyThrows //TODO Exception
    public User loginUser(UserDto loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new AccountNotFoundException("Account not found in database"));

        return checkCredentials(user, loginUser);
    }


    @SneakyThrows //TODO Exception
    private User checkCredentials(User loginUser, UserDto userFromDb) {
        if (bCryptPasswordEncoder.matches(loginUser.getPassword(), userFromDb.getPassword())) {
            return loginUser;
        }
        throw new PasswordsDontMatchException("Passwords do not match!");
    }
}
