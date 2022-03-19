package com.example.exercisespringdataautomappingobjects.service;

import com.example.exercisespringdataautomappingobjects.model.dto.GameAddDTO;
import com.example.exercisespringdataautomappingobjects.model.dto.GameEditDTO;

public interface GameService {
    void addGame(GameAddDTO gameAddDTO);

    void editGame(GameEditDTO gameEditDTO);

    void deleteGame(long parseLong);

    void getAllGames();

    void getDetailGame(String command);
}
