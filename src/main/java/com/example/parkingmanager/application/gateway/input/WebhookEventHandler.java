package com.example.parkingmanager.application.gateway.input;

import com.example.parkingmanager.application.dto.WebhookEventDto;

public interface WebhookEventHandler {
    boolean supports(String eventType);

    void handle(WebhookEventDto dto);
}
