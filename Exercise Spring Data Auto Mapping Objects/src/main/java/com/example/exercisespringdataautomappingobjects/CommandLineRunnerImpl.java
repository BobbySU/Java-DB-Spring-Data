package com.example.exercisespringdataautomappingobjects;

import com.example.exercisespringdataautomappingobjects.model.dto.GameAddDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.UserLoginDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.UserRegisterDTO;
import com.example.exercisespringdataautomappingobjects.service.GameService;
import com.example.exercisespringdataautomappingobjects.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("------ Enter your command: ------");
            String[] commands = bufferedReader.readLine().split("\\|");
            switch (commands[0]) {
                case "RegisterUser" -> userService
                        .registerUser(new UserRegisterDTO(commands[1], commands[2], commands[3], commands[4]));
                case "LoginUser" -> userService
                        .loginUser(new UserLoginDTO(commands[1], commands[2]));
                case "Logout" -> userService.logout();
                // TODO: 18/03/2022 Continue.... 
//                case "AddGame" -> gameService
//                        .addGame(new GameAddDTO(commands[1], new BigDecimal(commands[2]), Double.parseDouble(commands[3]),
//                                commands[4], commands[5], commands[6], commands[7]));


            }

        }
    }
}
