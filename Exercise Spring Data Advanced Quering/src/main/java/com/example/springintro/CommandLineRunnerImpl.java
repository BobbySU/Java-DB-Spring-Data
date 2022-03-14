package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

//        printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//        printAllAuthorsAndNumberOfTheirBooks();
//        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        System.out.println(" ------ Please Select Exercise/int/:");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum){
            case 1 -> booksTitlesByAge();
            case 2 -> goldenBooks();
            case 3 -> booksByPrice();
            case 4 -> notReleasedBooks();
            case 5 -> booksReleasedBeforeDate();
            case 6 -> authorsSearch();
            case 7 -> booksSearch();
            case 8 -> bookTitlesSearch();
            case 9 -> countBooks();
            case 10 -> totalBookCopies();
//            case 11 ->
        }


    }

    private void totalBookCopies() {
        
    }

    private void countBooks() throws IOException {
        System.out.println(" ------ Enter title is longer than a given number:");
        int num = Integer.parseInt(bufferedReader.readLine());
        int countOfBooks = bookService.findCountOfBooksWithNameLongerThen(num);
        System.out.println(countOfBooks);
        System.out.println("Comments:");
        System.out.printf("There are %d books with longer title than %d symbols%n", countOfBooks, num);
    }

    private void bookTitlesSearch() throws IOException {
        System.out.println(" ------ Enter authors last name starts with a given string:");
        String str = bufferedReader.readLine().toLowerCase();
        bookService.findAllBooksByAuthorLastNameStartWithString(str)
                .forEach(System.out::println);
    }

    private void booksSearch() throws IOException {
        System.out.println(" ------ Enter name contain a given string:");
        String str = bufferedReader.readLine().toLowerCase();
        bookService.findAllBooksContainGivenString(str)
                .forEach(System.out::println);
    }

    private void authorsSearch() throws IOException {
        System.out.println(" ------ Enter first name ends with a given string:");
        String firstNameStr = "%" + bufferedReader.readLine();
        authorService.getAllAuthorsWithGivenString(firstNameStr)
                .forEach(System.out::println);
    }

    private void booksReleasedBeforeDate() throws IOException {
        System.out.println(" ------ Enter Released Date in format dd-MM-yyyy:");
        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        bookService.findAllBooksReleasedBeforeDate(localDate)
                .forEach(System.out::println);
    }

    private void notReleasedBooks() throws IOException {
        System.out.println(" ------ Enter Released Year:");
        int year = Integer.parseInt(bufferedReader.readLine());
        bookService.findNotReleasedBooksInYear(year)
                .forEach(System.out::println);
    }

    private void booksByPrice() {
        System.out.println(" ------ Books by Price.");
        bookService.findAllBooksWithPriceLowerThen5OrHigherThen40()
                .forEach(System.out::println);
    }

    private void goldenBooks() {
        System.out.println(" ------ Golden Books less than 5000 copies.");
        bookService.findAllGoldBookWithCopiesLessThan5000()
                .forEach(System.out::println);
    }

    private void booksTitlesByAge() throws IOException {
        System.out.println(" ------ Enter Age Restriction:");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());

        bookService.findAllBooksWithAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
