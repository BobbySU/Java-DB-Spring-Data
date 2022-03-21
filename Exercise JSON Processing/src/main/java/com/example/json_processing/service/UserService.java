package com.example.json_processing.service;

import com.example.json_processing.model.dto.UserSoldDTO;
import com.example.json_processing.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUser() throws IOException;
    User findRandomUser();

    List<UserSoldDTO> findAllUserWithMoreThenOneSoldProduct();
}
