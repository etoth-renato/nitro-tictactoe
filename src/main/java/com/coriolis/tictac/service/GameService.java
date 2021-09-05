package com.coriolis.tictac.service;

import com.coriolis.tictac.controller.dto.StepRequest;
import com.coriolis.tictac.controller.dto.StepResponse;
import com.coriolis.tictac.data.FieldUI;
import com.coriolis.tictac.data.GameStep;
import com.coriolis.tictac.data.Scoreboard;
import com.coriolis.tictac.data.Side;
import com.coriolis.tictac.exception.GameNotFoundException;
import com.coriolis.tictac.repository.GameRepository;
import com.coriolis.tictac.repository.ScoreboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

  private final GameRepository gameRepository;
  private final ScoreboardRepository scoreboardRepository;

  private static final int BOARD_SIZE = 3;

  public StepResponse playerMove(StepRequest request) {
    GameStep gameStep = new GameStep();
    gameStep.setGameId(request.getGameId());
    gameStep.setId(UUID.randomUUID().toString());
    gameStep.setPlayer(request.getPlayer());
    gameStep.setX(request.getField().getX());
    gameStep.setY(request.getField().getY());

    gameRepository.save(gameStep);

    Optional<Side> winner = playerWin(gameStep.getGameId(), gameStep.getPlayer());

    winner.ifPresent(
        side -> scoreboardRepository.save(new Scoreboard(request.getGameId(), winner.get())));

    return StepResponse.builder()
        .gameOver(winner.isPresent())
        .gameId(gameStep.getGameId())
        .winner(winner.orElse(null))
        .build();
  }

  public List<FieldUI> parseBoard(String gameId) {
    Side[][] board = getBoard(gameId);
    List<FieldUI> result = new ArrayList<>();
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        result.add(new FieldUI(board[i][j], i, j));
      }
    }
    return result;
  }

  protected Side[][] getBoard(String gameId) {
    List<GameStep> steps =
        gameRepository
            .getGameStepsByGameId(gameId)
            .orElseThrow(() -> new GameNotFoundException("Game not found"));

    Side[][] board = new Side[BOARD_SIZE][BOARD_SIZE];

    steps.forEach(
        gameStep -> {
          board[gameStep.getX()][gameStep.getY()] = gameStep.getPlayer();
        });

    return board;
  }

  protected Optional<Side> playerWin(String gameId, Side player) {
    Side[][] board = getBoard(gameId);

    for (int i = 0; i < BOARD_SIZE; i++) {
      if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
        return Optional.of(player);
      }
    }

    for (int j = 0; j < BOARD_SIZE; j++)
      if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
        return Optional.of(player);
      }

    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
      return Optional.of(player);
    }

    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
      return Optional.of(player);
    }

    for (int i = 0; i < BOARD_SIZE; i++)
      for (int j = 0; j < BOARD_SIZE; j++) if (board[i][j] == null) return Optional.empty();

    return Optional.of(Side.TIE);
  }
}
