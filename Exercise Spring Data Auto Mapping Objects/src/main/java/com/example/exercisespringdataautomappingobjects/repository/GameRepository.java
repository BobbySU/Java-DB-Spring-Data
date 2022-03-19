package com.example.exercisespringdataautomappingobjects.repository;

import com.example.exercisespringdataautomappingobjects.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findById(Integer id);
    List<Game> findByTitle(String title);
}
