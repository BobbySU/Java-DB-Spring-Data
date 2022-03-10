package com.example.exercisespringdataintro.repositories;

import com.example.exercisespringdataintro.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBookByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findBookByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle
            (String firstName, String lastName);
}
