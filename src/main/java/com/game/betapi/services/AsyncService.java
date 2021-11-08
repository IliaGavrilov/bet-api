package com.game.betapi.services;

import com.game.betapi.model.BetRequest;
import com.game.betapi.model.BetResponse;
import com.game.betapi.model.ReturnedToPlayerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final RestTemplate restTemplate;

    @Value("${core.api.url}")
    private String url;

    @Async("asyncReturnedToPlayerExecutor")
    public void returnedToPlayer(ReturnedToPlayerRequest returnedToPlayerRequest, BetRequest betRequest) {
        returnedToPlayerRequest.setTotalInput(Integer.sum(returnedToPlayerRequest.getTotalInput(), betRequest.getInput()));

		Optional<BetResponse> betResponse = Optional.ofNullable(restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<>(betRequest), BetResponse.class).getBody());

        betResponse.ifPresent(response -> returnedToPlayerRequest.setTotalWinning(returnedToPlayerRequest.getTotalWinning()
                .add(response.getWinning())));
    }
}
