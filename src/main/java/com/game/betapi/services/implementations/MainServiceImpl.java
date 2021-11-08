package com.game.betapi.services.implementations;

import com.game.betapi.model.BetRequest;
import com.game.betapi.model.BetResponse;
import com.game.betapi.services.interfaces.MainService;
import com.game.betapi.utils.StatusCodes;
import com.game.betapi.utils.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final List<Predicate<BetRequest>> betRequestFilteringPredicates;
    private final OperationsService operationsService;

    @Override
    public boolean isBetRequestParamsInRange(BetRequest betRequest) {
        return betRequestFilteringPredicates.stream().map(e -> e.test(betRequest))
                .collect(Collectors.toCollection(ArrayList::new)).contains(false);
    }

    @Override
    public BetResponse doBasicApiOperation(BetRequest betRequest, OperationsService.BetApiOperation betApiOperation) {
        Integer random = new Random().nextInt(100);
        if (betRequest.getInput() < random) {
            return buildErrorBetResponse(StatusCode.SUCCESS.getValue());
        }

        return operationsService.execute(betRequest, betApiOperation);
    }


    private BetResponse buildErrorBetResponse(String errorCode) {
        return BetResponse.builder()
                .errorCode(errorCode)
                .errorMessage(StatusCodes.getValue(errorCode))
                .winning(new BigDecimal(0).setScale(2))
                .build();
    }
}
