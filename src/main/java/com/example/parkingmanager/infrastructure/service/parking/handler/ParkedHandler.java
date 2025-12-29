package com.example.parkingmanager.infrastructure.service.parking.handler;

import com.example.parkingmanager.application.dto.WebhookEventDto;
import com.example.parkingmanager.application.gateway.input.WebhookEventHandler;
import com.example.parkingmanager.infrastructure.repository.ParkingSessionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ParkedHandler implements WebhookEventHandler {

    private final ParkingSessionRepository repo;

    public ParkedHandler(ParkingSessionRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean supports(String eventType) {
        return "PARKED".equalsIgnoreCase(eventType);
    }

    @Override
    public void handle(WebhookEventDto dto) {
        repo.findByLicensePlateAndClosedFalse(dto.licensePlate())
                .stream().findFirst()
                .ifPresent(s -> {
                    s.setParkedTime(Instant.now());
                    repo.save(s);
                });
    }
}
