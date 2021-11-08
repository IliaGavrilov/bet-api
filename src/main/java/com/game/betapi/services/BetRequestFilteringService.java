package com.game.betapi.services;

import com.game.betapi.model.BetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
@RequiredArgsConstructor
public class BetRequestFilteringService {

    @Bean
    public Predicate<BetRequest> isBetRequestParamsInRange() {
        return c -> isInRange(c.getInput()) && isInRange(c.getBet());
    }

    private boolean isInRange(Integer input) {
        return 0 <= input && input <= 100;
    }

    private boolean isInRange(Double bet) {
        return 0 <= bet && bet <= 100;
    }

}
