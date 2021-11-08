package com.game.betapi.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileValuesReader {

    public Map<String, String> readFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            Map<String, String> mappingValues = new HashMap<>();
            String line;
            String splitBy = "=";
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] values = line.split(splitBy);
                    mappingValues.put(values[0], values[1]);
                }
            }
            return mappingValues;
        }
    }

}
