package com.example.parkingmanager.service.parking.handler;

import com.example.parkingmanager.dto.WebhookEventDto;
import com.example.parkingmanager.repository.ParkingSessionRepository;
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
