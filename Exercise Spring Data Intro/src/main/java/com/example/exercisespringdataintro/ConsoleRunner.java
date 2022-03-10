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

// TODO: 10/03/2022   ------ Select the Method you want to TEST -------
//        printAllBooksAfterYear(2000);
//        printAllAuthorsWithBooksBeforeYear(1990);
//        printAllAuthorsAndThereBookCount();
//        printAllBooksByAuthorOrderByDate("George", "Powell");
    }

    private void printAllBooksByAuthorOrderByDate(String firstName, String lastName) {
        bookService.findAllBooksByAuthorOrderByDateAndTitle(firstName,lastName)
                .forEach(e->System.out.printf("%s %s %d%n", e.getTitle(), e.getReleaseDate(),e.getCopies()));
    }

    private void printAllAuthorsAndThereBookCount() {
        authorService.getAllAuthorsAndThereBookCount()
                .forEach(e -> System.out.printf("%s %s %d%n", e.getFirstName(), e.getLastName(), e.books.size()));
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
