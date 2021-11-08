package com.game.betapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BetResponse extends BaseResponse {
    @JsonProperty("winning")
    private BigDecimal winning;
}
