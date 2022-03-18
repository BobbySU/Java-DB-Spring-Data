package com.example.exercisespringdataautomappingobjects.repository;

import com.example.exercisespringdataautomappingobjects.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
