package ua.com.foodtrackerfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.foodtrackerfinal.Exception.PasswordsException;
import ua.com.foodtrackerfinal.Exception.UsernameFoundException;
import ua.com.foodtrackerfinal.entity.User;
import ua.com.foodtrackerfinal.repository.RoleRepository;
import ua.com.foodtrackerfinal.repository.UserRepository;

import java.util.Optional;

@Service
public class RegistrationService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean registerUser(User user) throws UsernameFoundException, PasswordsException {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()){
            throw new UsernameFoundException("Account with this email already exists!");
        }
        user.setUsername(user.getUsername());

        if (checkPasswordsMatch(user)){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            throw new PasswordsException("Passwords don't match!");
        }

        user.setRoles(user.getRoles());
        user.setHeight(user.getHeight());
        user.setWeight(user.getWeight());
        System.out.println("New user was created"); //TODO delete this line
        return true;
    }

    public boolean checkPasswordsMatch(User user){
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
