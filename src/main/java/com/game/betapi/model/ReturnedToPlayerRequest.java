package com.game.betapi.model;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReturnedToPlayerRequest {

    private volatile Integer totalInput;
    private volatile BigDecimal totalWinning;

}
