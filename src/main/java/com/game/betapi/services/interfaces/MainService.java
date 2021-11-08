package com.game.betapi.services.interfaces;

import com.game.betapi.model.BetRequest;
import com.game.betapi.model.BetResponse;
import com.game.betapi.services.implementations.OperationsService;

public interface MainService {

    boolean isBetRequestParamsInRange(BetRequest betRequest);

    BetResponse doBasicApiOperation(BetRequest betRequest, OperationsService.BetApiOperation betApiOperation);

}
