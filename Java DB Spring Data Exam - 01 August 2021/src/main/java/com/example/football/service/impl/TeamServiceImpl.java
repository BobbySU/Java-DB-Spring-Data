package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDTO;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

//ToDo - Implement all methods
@Service
public class TeamServiceImpl implements TeamService {
    private static final String TEAMS_FILE_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAMS_FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readTeamsFileContent(), TeamSeedDTO[].class))
                .filter(teamSeedDTO -> {
                    boolean isValid = validationUtil.isValid(teamSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Team %s - %d",
                                    teamSeedDTO.getName(), teamSeedDTO.getFanBase())
                                    : "Invalid Team")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(teamSeedDTO -> modelMapper.map(teamSeedDTO, Team.class))
                .forEach(teamRepository::save);
        return sb.toString();
    }
}
