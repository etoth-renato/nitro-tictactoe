package com.coriolis.tictac.controller;

import com.coriolis.tictac.repository.ScoreBoardItem;
import com.coriolis.tictac.service.ScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scoreboard")
public class ScoreBoardController {

  private final ScoreBoardService scoreBoardService;

  @GetMapping
  public List<ScoreBoardItem> getScores() {
    return scoreBoardService.getScoreBoardItems();
  }
}
