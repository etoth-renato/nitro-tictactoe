package com.coriolis.tictac.repository;

import com.coriolis.tictac.data.GameStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameStep, String> {

  @Query("select u from GameStep u where u.gameId = :gameId order by u.x,u.y")
  Optional<List<GameStep>> getGameStepsByGameId(String gameId);
}
