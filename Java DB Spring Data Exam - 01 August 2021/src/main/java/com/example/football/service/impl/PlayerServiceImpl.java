package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedRootDTO;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownService townService, TeamService teamService, StatService statService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PLAYERS_FILE_PATH, PlayerSeedRootDTO.class)
                .getPlayerSeedDTOS()
                .stream()
                .filter(playerSeedDTO -> {
                    boolean isValid = validationUtil.isValid(playerSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Player %s %s - %s",
                                    playerSeedDTO.getFirstName(), playerSeedDTO.getLastName(), playerSeedDTO.getPosition())
                                    : "Invalid Player")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(playerSeedDTO -> {
                    Player player = modelMapper.map(playerSeedDTO, Player.class);
                        player.setTown(townService.FindTownByName(playerSeedDTO.getTown().getName()));
                        player.setTeam(teamService.FindTeamByName(playerSeedDTO.getTeam().getName()));
                        player.setStat(statService.FindStatById(playerSeedDTO.getStat().getId()));
                    return player;
                })
                .forEach(playerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}
