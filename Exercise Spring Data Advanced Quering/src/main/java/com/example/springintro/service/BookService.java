package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBooksWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllGoldBookWithCopiesLessThan5000();

    List<String> findAllBooksWithPriceLowerThen5OrHigherThen40();

    List<String> findNotReleasedBooksInYear(int year);

    List<String> findAllBooksReleasedBeforeDate(LocalDate localDate);

    List<String> findAllBooksContainGivenString(String str);

    List<String> findAllBooksByAuthorLastNameStartWithString(String str);

    int findCountOfBooksWithNameLongerThen(int num);

    String findBookByTitle(String title);
}
