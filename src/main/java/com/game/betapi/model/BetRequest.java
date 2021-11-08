package com.game.betapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BetRequest {
    private Integer input;
    private Double bet;
}
