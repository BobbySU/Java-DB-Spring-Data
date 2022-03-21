package com.example.json_processing.service;

import com.example.json_processing.model.entity.User;

import java.io.IOException;

public interface UserService {
    void seedUser() throws IOException;
    User findRandomUser();
}
