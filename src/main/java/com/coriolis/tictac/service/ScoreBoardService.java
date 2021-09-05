package com.coriolis.tictac.service;

import com.coriolis.tictac.repository.ScoreBoardItem;
import com.coriolis.tictac.repository.ScoreboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreBoardService {

  private final ScoreboardRepository scoreboardRepository;

  public List<ScoreBoardItem> getScoreBoardItems() {
    return scoreboardRepository.getScoreBoards();
  }
}
