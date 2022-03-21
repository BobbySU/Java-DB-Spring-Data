package com.example.json_processing.service.impl;

import com.example.json_processing.model.dto.UserSeedDTO;
import com.example.json_processing.model.entity.User;
import com.example.json_processing.repository.UserRepository;
import com.example.json_processing.service.UserService;
import com.example.json_processing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.json_processing.constants.GlobalConstants.RESOURCES_FILE_PATH;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FILE = "users.json";

    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, Gson gson,
                           ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUser() throws IOException {
        if (userRepository.count() == 0) {
            Arrays.stream(gson.fromJson(
                            Files.readString(Path.of(RESOURCES_FILE_PATH + USER_FILE)), UserSeedDTO[].class))
                    .filter(validationUtil::isValid)
                    .map(UserSeedDTO -> modelMapper.map(UserSeedDTO, User.class))
                    .forEach(userRepository::save);
        }
    }

    @Override
    public User findRandomUser() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository.findById(randomId).orElse(null);
    }
}
