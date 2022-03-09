package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.models.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<Book> findAllBooksABeforeYear(int year);
}
