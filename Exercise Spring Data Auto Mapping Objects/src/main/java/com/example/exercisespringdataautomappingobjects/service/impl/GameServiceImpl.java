package com.example.exercisespringdataautomappingobjects.service.impl;

import com.example.exercisespringdataautomappingobjects.model.dto.GameAddDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.GameEditDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.UserLoginDTO;
import com.example.exercisespringdataautomappingobjects.model.entity.Game;
import com.example.exercisespringdataautomappingobjects.repository.GameRepository;
import com.example.exercisespringdataautomappingobjects.service.GameService;
import com.example.exercisespringdataautomappingobjects.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
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

        gameRepository.save(game);

        System.out.printf("Added %s%n", game.getTitle());
    }

    @Override
    public void editGame(GameEditDTO gameEditDTO) {
        Set<ConstraintViolation<GameEditDTO>> violations = validationUtil.violation(gameEditDTO);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        Game game = gameRepository.findById(gameEditDTO.getId()).orElse(null);

        if (game == null){
            System.out.println("Incorrect Game ID");
            return;
        }

        game.setPrice(gameEditDTO.getPrice());
        game.setSize(gameEditDTO.getSize());
        gameRepository.save(game);

        System.out.printf("Edited %s%n", game.getTitle());
    }

    @Override
    public void deleteGame(long parseLong) {
        Game game = gameRepository.findById(parseLong).orElse(null);

        if (game == null){
            System.out.println("Incorrect Game ID");
            return;
        }
        String name = game.getTitle();
        gameRepository.delete(game);

        System.out.printf("Deleted %s%n", name);
    }

    @Override
    public void getAllGames() {
        List<Game> games = gameRepository.findAll();

        if (games.isEmpty()){
            System.out.println("No Games");
            return;
        }

        games.stream().forEach(game -> System.out.printf("%s %.2f%n",game.getTitle(),game.getPrice()));
    }

    @Override
    public void getDetailGame(String command) {
        Game game = gameRepository.findByTitle(command).orElse(null);

        if (game == null){
            System.out.println("Incorrect Game Title");
            return;
        }

        System.out.printf("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s%n",
                game.getTitle(),game.getPrice(),game.getDescription(),game.getReleaseDate());
    }

}
