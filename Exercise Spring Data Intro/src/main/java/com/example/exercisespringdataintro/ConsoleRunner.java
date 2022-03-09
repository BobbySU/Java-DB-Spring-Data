package com.example.exercisespringdataintro;


import com.example.exercisespringdataintro.models.Book;
import com.example.exercisespringdataintro.services.AuthorService;
import com.example.exercisespringdataintro.services.BookService;
import com.example.exercisespringdataintro.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public ConsoleRunner(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

//        printAllBooksAfterYear(2000);
//        printAllAuthorsWithBooksBeforeYear(1990);
//        printAllAuthorsAndThereBookCount();

    }

    private void printAllAuthorsWithBooksBeforeYear(int year) {
        bookService.findAllBooksABeforeYear(year).stream().map(Book::getAuthor).distinct()
                .forEach(e -> System.out.printf("%s %s%n", e.getFirstName(), e.getLastName()));
    }

    private void printAllBooksAfterYear(int year) {
        bookService.findAllBooksAfterYear(year).stream().map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
