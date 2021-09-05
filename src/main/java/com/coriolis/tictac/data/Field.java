package com.coriolis.tictac.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field {
  private int x;
  private int y;

  @Override
  public String toString() {
    return "[" + x + ";" + y + "]";
  }
}
