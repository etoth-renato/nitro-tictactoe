package com.coriolis.tictac.controller;

import com.coriolis.tictac.controller.dto.StepRequest;
import com.coriolis.tictac.controller.dto.StepResponse;
import com.coriolis.tictac.data.Field;
import com.coriolis.tictac.data.FieldUI;
import com.coriolis.tictac.data.Side;
import com.coriolis.tictac.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/game")
public class GameController {

  private final GameService gameService;

  @GetMapping("/{gameId}")
  public List<FieldUI> getBoard(@PathVariable("gameId") @NotEmpty String gameId) {
    return gameService.parseBoard(gameId);
  }

  @PostMapping
  public StepResponse move(@RequestBody @Valid StepRequest request) {
    return gameService.playerMove(request);
  }
}
