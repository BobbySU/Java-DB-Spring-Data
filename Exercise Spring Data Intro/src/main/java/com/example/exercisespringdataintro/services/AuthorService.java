package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.models.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
