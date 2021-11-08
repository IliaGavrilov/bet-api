package com.game.betapi.services.implementations;

import com.game.betapi.model.BetRequest;
import com.game.betapi.model.BetResponse;
import com.game.betapi.services.interfaces.ApiService;
import com.game.betapi.utils.StatusCodes;
import com.game.betapi.utils.enums.StatusCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BetService implements ApiService {

    @Override
    public BetResponse processInputAndBet(BetRequest betRequest) {

        return BetResponse.builder()
                .errorCode(StatusCode.SUCCESS.getValue())
                .errorMessage(StatusCodes.getValue(StatusCode.SUCCESS.getValue()))
                .winning(calculateWinning(betRequest))
                .build();
    }

    private BigDecimal calculateWinning(BetRequest betRequest) {
        int x = 100 - betRequest.getInput();
        BigDecimal y = BigDecimal.valueOf(99).divide(BigDecimal.valueOf(x), 2, RoundingMode.HALF_UP);
        return BigDecimal.valueOf(betRequest.getBet()).multiply(y).setScale(2, RoundingMode.HALF_UP);
    }
}
