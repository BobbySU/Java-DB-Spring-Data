package com.example.xml_processing.service;

import com.example.xml_processing.repository.model.dto.seed.UserSeedDTO;
import com.example.xml_processing.repository.model.entity.User;

import java.util.List;

public interface UserService {
    long getEntityCount();

    void seedUser(List<UserSeedDTO> users);

    User findRandomUser();

//    List<UserSoldDTO> findAllUserWithMoreThenOneSoldProduct();
}