package com.coriolis.tictac.repository;

import com.coriolis.tictac.data.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreboardRepository extends JpaRepository<Scoreboard, String> {

  @Query("select s.win as side, count(s.win) as score from Scoreboard s group by s.win")
  List<ScoreBoardItem> getScoreBoards();
}
