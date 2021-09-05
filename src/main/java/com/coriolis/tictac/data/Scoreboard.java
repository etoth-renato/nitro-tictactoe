package com.coriolis.tictac.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Scoreboard {
  @EqualsAndHashCode.Include @Id private String gameId;

  @Enumerated(EnumType.STRING)
  private Side win;
}
