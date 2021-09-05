package com.coriolis.tictac.controller.dto;

import com.coriolis.tictac.data.Side;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepResponse {
  private String gameId;
  private boolean gameOver;
  private Side winner;
}
