package com.coriolis.tictac;

import com.coriolis.tictac.controller.dto.StepRequest;
import com.coriolis.tictac.data.Field;
import com.coriolis.tictac.data.GameStep;
import com.coriolis.tictac.data.Side;
import com.coriolis.tictac.repository.GameRepository;
import com.coriolis.tictac.repository.ScoreboardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TicTacToeApplicationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private GameRepository gameRepository;

  @Autowired private ScoreboardRepository scoreboardRepository;

  @Test
  void contextLoads() {}

  @Test
  public void getBoardTest() throws Exception {
    MvcResult result =
        mockMvc
            .perform(get("/api/game/asd").contentType("application/json"))
            .andExpect(status().isOk()).andReturn();

    assertFalse(result.getResponse().getContentAsString().isEmpty());
  }

  @Test
  public void legalMoveTest() throws Exception {
    StepRequest request = new StepRequest();
    request.setField(new Field(0, 0));
    request.setPlayer(Side.O);
    request.setGameId(UUID.randomUUID().toString());

    mockMvc
        .perform(
            post("/api/game")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());

    Optional<List<GameStep>> gameSteps = gameRepository.getGameStepsByGameId(request.getGameId());
    assertTrue(gameSteps.isPresent());
  }

  @Test
  public void playerWinColOneTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 0));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(1, 0));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(2, 0));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinColTwoTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 1));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(1, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(2, 1));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinColThreeTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 2));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(1, 2));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(2, 2));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinRowOneTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 0));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(0, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(0, 2));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinRowTwoTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(1, 0));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(1, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(1, 2));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinRowThreeTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(2, 0));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(2, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(2, 2));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinCrossOneTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 0));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(1, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(2, 2));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerWinCrossTwoTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 2));
    request.setPlayer(Side.O);
    request.setGameId(gameId);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(1, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(2, 0));
    request3.setPlayer(Side.O);
    request3.setGameId(gameId);

    moveAndTryWin(request, request2, request3);
  }

  @Test
  public void playerTieTest() throws Exception {
    String gameId = UUID.randomUUID().toString();

    StepRequest request = new StepRequest();
    request.setField(new Field(0, 0));
    request.setPlayer(Side.O);
    request.setGameId(gameId);
    makeMove(request);

    StepRequest request2 = new StepRequest();
    request2.setField(new Field(0, 1));
    request2.setPlayer(Side.O);
    request2.setGameId(gameId);
    makeMove(request2);

    StepRequest request3 = new StepRequest();
    request3.setField(new Field(0, 2));
    request3.setPlayer(Side.X);
    request3.setGameId(gameId);
    makeMove(request3);

    StepRequest request4 = new StepRequest();
    request4.setField(new Field(1, 0));
    request4.setPlayer(Side.X);
    request4.setGameId(gameId);
    makeMove(request4);

    StepRequest request5 = new StepRequest();
    request5.setField(new Field(1, 1));
    request5.setPlayer(Side.X);
    request5.setGameId(gameId);
    makeMove(request5);

    StepRequest request6 = new StepRequest();
    request6.setField(new Field(1, 2));
    request6.setPlayer(Side.O);
    request6.setGameId(gameId);
    makeMove(request6);

    StepRequest request7 = new StepRequest();
    request7.setField(new Field(2, 0));
    request7.setPlayer(Side.O);
    request7.setGameId(gameId);
    makeMove(request7);

    StepRequest request8 = new StepRequest();
    request8.setField(new Field(2, 1));
    request8.setPlayer(Side.X);
    request8.setGameId(gameId);
    makeMove(request8);

    StepRequest request9 = new StepRequest();
    request9.setField(new Field(2, 2));
    request9.setPlayer(Side.X);
    request9.setGameId(gameId);
    makeMove(request9);

    Optional<List<GameStep>> gameSteps = gameRepository.getGameStepsByGameId(gameId);
    assertTrue(gameSteps.isPresent());
    assertEquals(9, gameSteps.get().size());

    assertTrue(getScores().getResponse().getContentAsString().contains("\"score\":1"));
    assertTrue(getScores().getResponse().getContentAsString().contains("\"side\":\"TIE\""));
  }

  private void moveAndTryWin(StepRequest request, StepRequest request2, StepRequest request3)
      throws Exception {
    makeMove(request);
    makeMove(request2);
    makeMove(request3);
    Optional<List<GameStep>> gameSteps = gameRepository.getGameStepsByGameId(request.getGameId());
    assertTrue(gameSteps.isPresent());
    assertEquals(3, gameSteps.get().size());

    assertTrue(getScores().getResponse().getContentAsString().contains("\"score\":1"));
    assertTrue(getScores().getResponse().getContentAsString().contains("\"side\":\"O\""));
  }

  private void makeMove(StepRequest request) throws Exception {
    mockMvc
        .perform(
            post("/api/game")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  private MvcResult getScores() throws Exception {
    return mockMvc.perform(get("/api/scoreboard")).andExpect(status().isOk()).andReturn();
  }
}
