package com.game.betapi.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import com.game.betapi.utils.enums.ConfigFilePath;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class StatusCodesInitialization {

    private final FileValuesReader fileValuesReader;

    @EventListener(ApplicationReadyEvent.class)
    public void initErrorCodes() {
        try {
            StatusCodes.setStatusValues(fileValuesReader.readFile(ConfigFilePath.STATUS_CODES.getValue()));
        } catch (IOException e) {
            throw new IllegalStateException("StatusValues was not initialized.");
        }
    }

}
