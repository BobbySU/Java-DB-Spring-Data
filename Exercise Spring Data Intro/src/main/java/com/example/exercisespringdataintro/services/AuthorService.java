package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.models.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<Author> getAllAuthorsAndThereBookCount();
}
