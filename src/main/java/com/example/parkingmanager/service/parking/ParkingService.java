package com.example.parkingmanager.service.parking;

import com.example.parkingmanager.dto.WebhookEventDto;
import com.example.parkingmanager.service.parking.handler.WebhookEventHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    private final List<WebhookEventHandler> handlers;

    public ParkingService(List<WebhookEventHandler> handlers) {
        this.handlers = handlers;
    }

    public void process(WebhookEventDto dto) {
        handlers.stream()
                .filter(h -> h.supports(dto.eventType()))
                .findFirst()
                .ifPresent(h -> h.handle(dto));
    }
}
