package ua.com.foodtrackerfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.foodtrackerfinal.Exception.PasswordsException;
import ua.com.foodtrackerfinal.Exception.UsernameFoundException;
import ua.com.foodtrackerfinal.dto.UserDto;
import ua.com.foodtrackerfinal.entity.Role;
import ua.com.foodtrackerfinal.entity.User;
import ua.com.foodtrackerfinal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
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

    public boolean registerUser(UserDto userDto) throws UsernameFoundException, PasswordsException {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new UsernameFoundException("Account with this email already exists!");
        }

        User newUser = new User();

        newUser.setUsername(userDto.getUsername());

        if (checkPasswordsMatch(userDto)) {
            newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        } else {
            throw new PasswordsException("Passwords don't match!");
        }

        newUser.setRole(createListWithRoleUser());

        newUser.setHeight(userDto.getHeight());
        newUser.setWeight(userDto.getWeight());
        userRepository.save(newUser);
        System.out.println("New user was created"); //TODO delete this line
        return true;
    }

    private boolean checkPasswordsMatch(UserDto userDto) {
        return !userDto.getPassword().equals(userDto.getConfirmedPassword());
    }

    private List<Role> createListWithRoleUser() {
        List<Role> listWithOneRole = new ArrayList<>();
        listWithOneRole.add(new Role("USER"));
        return listWithOneRole;
    }
}
