package com.game.betapi.services.implementations;

import com.game.betapi.model.BetRequest;
import com.game.betapi.model.BetResponse;
import com.game.betapi.services.interfaces.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class OperationsService {

    private final BetService betService;

    @PostConstruct
    private void postConstruct() {
        EnumSet.allOf(BetApiOperation.class).forEach(betOperation -> betOperation.setResponsibleService(betService));
    }
    public BetResponse execute(BetRequest betRequest, ApiOperation operation) {
        return operation.executeOperation(betRequest);
    }

    public enum BetApiOperation implements ApiOperation {

        GET_WINNING_BY_INPUT_AND_BET {
            @Override
            public BetResponse executeOperation(BetRequest betRequest) {
                return getApiService().processInputAndBet(betRequest);
            }
        };

        private BetService apiService;

        public BetService getApiService() {
            return this.apiService;
        }

        private void setResponsibleService(BetService apiService) {
            this.apiService = apiService;
        }
    }
}
