package com.coriolis.tictac.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldUI {
  private Side side;
  private int x;
  private int y;
}
