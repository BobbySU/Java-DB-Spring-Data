package com.example.exercisespringdataautomappingobjects.service.impl;

import com.example.exercisespringdataautomappingobjects.model.dto.UserLoginDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.UserRegisterDTO;
import com.example.exercisespringdataautomappingobjects.model.entity.User;
import com.example.exercisespringdataautomappingobjects.repository.UserRepository;
import com.example.exercisespringdataautomappingobjects.service.UserService;
import com.example.exercisespringdataautomappingobjects.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            System.out.println("Incorrect confirm password!");
            return;
        }

        Set<ConstraintViolation<UserRegisterDTO>> violations = validationUtil.violation(userRegisterDTO);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDTO,User.class);
        userRepository.save(user);
        System.out.printf("%s was registered%n", user.getFullName());
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {

        Set<ConstraintViolation<UserLoginDTO>> violations = validationUtil.violation(userLoginDTO);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = userRepository.findByEmailAndPassword(userLoginDTO.getEmail(),userLoginDTO.getPassword())
                .orElse(null);

        if (user==null){
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;
        System.out.printf("Successfully logged in %s%n", user.getFullName());
    }

    @Override
    public void logout() {
        if (loggedInUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            loggedInUser = null;
            System.out.println("Successfully log out.");
        }
    }
}
