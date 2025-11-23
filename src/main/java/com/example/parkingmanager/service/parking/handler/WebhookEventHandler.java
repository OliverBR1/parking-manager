package com.example.parkingmanager.service.parking.handler;

import com.example.parkingmanager.dto.WebhookEventDto;

public interface WebhookEventHandler {
    boolean supports(String eventType);

    void handle(WebhookEventDto dto);
}
