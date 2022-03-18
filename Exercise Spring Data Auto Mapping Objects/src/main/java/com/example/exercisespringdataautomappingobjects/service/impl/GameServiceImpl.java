package com.example.exercisespringdataautomappingobjects.service.impl;

import com.example.exercisespringdataautomappingobjects.model.dto.GameAddDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.UserLoginDTO;
import com.example.exercisespringdataautomappingobjects.model.entity.Game;
import com.example.exercisespringdataautomappingobjects.repository.GameRepository;
import com.example.exercisespringdataautomappingobjects.service.GameService;
import com.example.exercisespringdataautomappingobjects.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDTO gameAddDTO) {
        Set<ConstraintViolation<GameAddDTO>> violations = validationUtil.violation(gameAddDTO);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDTO,Game.class);

    }
}
