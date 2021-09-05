package com.coriolis.tictac.controller.dto;

import com.coriolis.tictac.data.Field;
import com.coriolis.tictac.data.Side;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StepRequest {
  @NotEmpty private String gameId;
  @NotNull private Side player;
  @NotNull private Field field;
}
