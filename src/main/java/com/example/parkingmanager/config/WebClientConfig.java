package com.example.parkingmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.env.Environment;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient simulatorWebClient(Environment env) {
        String base = env.getProperty("simulator.base-url");

        if (base == null) {
            throw new IllegalStateException("simulator.base-url is not configured!");
        }

        return WebClient.builder().baseUrl(base).build();
    }

}
