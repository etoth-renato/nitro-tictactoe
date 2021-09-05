package com.coriolis.tictac.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GameStep {
  @EqualsAndHashCode.Include @Id private String id;

  private String gameId;
  private Integer x;
  private Integer y;
  private Side player;
}
