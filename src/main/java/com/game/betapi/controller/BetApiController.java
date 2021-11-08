package com.game.betapi.controller;

import com.game.betapi.model.BaseResponse;
import com.game.betapi.model.BetRequest;
import com.game.betapi.services.implementations.OperationsService;
import com.game.betapi.services.interfaces.MainService;
import com.game.betapi.utils.StatusCodes;
import com.game.betapi.utils.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BetApiController {

    private final MainService mainService;

    @PostMapping(value = "/getWinning", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends BaseResponse> getWinning(@RequestBody BetRequest betRequest) {

        if (mainService.isBetRequestParamsInRange(betRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.builder()
                            .errorCode(StatusCode.OUT_OF_BOUNDS_PARAMS.getValue())
                            .errorMessage(StatusCodes.getValue(StatusCode.OUT_OF_BOUNDS_PARAMS.getValue()))
                            .build());
        }

        return ResponseEntity.ok(mainService.doBasicApiOperation(betRequest,
                OperationsService.BetApiOperation.GET_WINNING_BY_INPUT_AND_BET));
    }

    @ExceptionHandler()
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.builder()
                        .errorCode(StatusCode.INTERNAL_SERVICE_ERROR.getValue())
                        .errorMessage(StatusCodes.getValue(StatusCode.INTERNAL_SERVICE_ERROR.getValue()))
                        .build());
    }
}
