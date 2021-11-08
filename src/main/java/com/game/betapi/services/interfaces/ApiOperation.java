package com.game.betapi.services.interfaces;

import com.game.betapi.model.BetRequest;
import com.game.betapi.model.BetResponse;

public interface ApiOperation {

    BetResponse executeOperation(BetRequest betRequest);

}
