package com.example.backend.Vision;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.FileInputStream;

@Configuration
public class VisionConfig {
    
    @Value("${ocr-space.api.key}")
    private String apiKey;

    @Value("${ocr-space.api.endpoint}")
    private String apiEndpoint;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }
}
