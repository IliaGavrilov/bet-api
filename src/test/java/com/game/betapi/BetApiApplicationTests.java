package com.game.betapi;

import com.game.betapi.controller.BetApiController;
import com.game.betapi.model.BetRequest;
import com.game.betapi.model.ReturnedToPlayerRequest;
import com.game.betapi.services.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RequiredArgsConstructor
@Service
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "local")
class BetApiApplicationTests {

	@Value("${core.api.rounds}")
	private int rounds;

	@Autowired
	private ThreadPoolTaskExecutor asyncReturnedToPlayerExecutor;

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private BetApiController betApiController;

	BigDecimal returnedToPlayer = BigDecimal.ZERO;

	@Test
	void contextLoads() {
		assertThat(betApiController).isNotNull();
	}

	@Test
	void returnedToPlayer() throws Exception {
		ReturnedToPlayerRequest returnedToPlayerRequest = ReturnedToPlayerRequest.builder()
				.totalInput(0).totalWinning(BigDecimal.ZERO).build();

		for (int i = 0; i < rounds; i++) {
			asyncService.returnedToPlayer(returnedToPlayerRequest, buildBetRequest());
			log.info("iter: {}", i);
			asyncReturnedToPlayerExecutor.getThreadPoolExecutor().awaitTermination(10, TimeUnit.MILLISECONDS);
		}

		returnedToPlayer = returnedToPlayerRequest.getTotalWinning().multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP)
				.divide(BigDecimal.valueOf(returnedToPlayerRequest.getTotalInput()), 2, RoundingMode.HALF_UP);

		log.info("Rounds: {}", rounds);
		log.info("Total input: {}", returnedToPlayerRequest.getTotalInput());
		log.info("Total winning: {}", returnedToPlayerRequest.getTotalWinning());
		log.info("RTP: {}", returnedToPlayer);
	}

	private BetRequest buildBetRequest() {
		int randomInput = new Random().nextInt(100);
		double randomBet = BigDecimal.valueOf(new Random().nextDouble() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return new BetRequest(randomInput, randomBet);
	}
}
