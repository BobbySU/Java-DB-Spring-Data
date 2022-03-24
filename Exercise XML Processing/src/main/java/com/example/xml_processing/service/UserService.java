package com.example.xml_processing.service;

import com.example.xml_processing.model.dto.UserSoldRootDTO;
import com.example.xml_processing.model.dto.seed.UserSeedDTO;
import com.example.xml_processing.model.entity.User;

import java.util.List;

public interface UserService {
    long getEntityCount();

    void seedUser(List<UserSeedDTO> users);

    User findRandomUser();

    UserSoldRootDTO findAllUserWithMoreThenOneSoldProduct();
}
