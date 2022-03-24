package com.example.xml_processing.service.impl;

import com.example.xml_processing.model.dto.UserSoldDTO;
import com.example.xml_processing.model.dto.UserSoldRootDTO;
import com.example.xml_processing.repository.UserRepository;
import com.example.xml_processing.model.dto.seed.UserSeedDTO;
import com.example.xml_processing.model.entity.User;
import com.example.xml_processing.service.UserService;
import com.example.xml_processing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public long getEntityCount() {
        return userRepository.count();
    }

    @Override
    public void seedUser(List<UserSeedDTO> users) {
        users.stream()
                .filter(validationUtil::isValid)
                .map(UserSeedDTO -> modelMapper.map(UserSeedDTO, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User findRandomUser() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository.findById(randomId).orElse(null);
    }

    @Override
    public UserSoldRootDTO findAllUserWithMoreThenOneSoldProduct() {
        UserSoldRootDTO userSoldRootDTO = new UserSoldRootDTO();
        userSoldRootDTO.setProducts(userRepository.findAllWithSoldProductOrderByLastAndFirstName()
                .stream()
                .map(user -> modelMapper.map(user, UserSoldDTO.class))
                .collect(Collectors.toList()));
        return userSoldRootDTO;
    }
}
