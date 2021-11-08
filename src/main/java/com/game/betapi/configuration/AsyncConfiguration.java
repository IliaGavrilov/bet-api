package com.game.betapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfiguration implements AsyncConfigurer {

    @Value("${core.api.pool.size}")
    private int poolSize;

    @Bean("asyncReturnedToPlayerExecutor")
    public Executor asyncReturnedToPlayerExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setThreadNamePrefix("ReturnedToPlayerThread-");
        executor.initialize();
        return executor;
    }
}
