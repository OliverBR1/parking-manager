package com.example.parkingmanager.infrastructure.service.parking.handler;

import com.example.parkingmanager.application.dto.WebhookEventDto;
import com.example.parkingmanager.application.gateway.input.WebhookEventHandler;
import com.example.parkingmanager.infrastructure.repository.ParkingSessionRepository;
import com.example.parkingmanager.infrastructure.service.parking.logic.BillingService;
import com.example.parkingmanager.infrastructure.service.parking.logic.SpotAllocationService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ExitHandler implements WebhookEventHandler {

    private final ParkingSessionRepository repo;
    private final BillingService billingService;
    private final SpotAllocationService allocationService;

    public ExitHandler(ParkingSessionRepository repo,
                       BillingService billingService,
                       SpotAllocationService allocationService) {
        this.repo = repo;
        this.billingService = billingService;
        this.allocationService = allocationService;
    }

    @Override
    public boolean supports(String eventType) {
        return "EXIT".equalsIgnoreCase(eventType);
    }

    @Override
    public void handle(WebhookEventDto dto) {
        repo.findByLicensePlateAndClosedFalse(dto.licensePlate())
                .stream().findFirst()
                .ifPresent(session -> {
                    Instant exit;
                    try {
                        exit = Instant.parse(dto.exitTime());
                    } catch (Exception e) {
                        exit = Instant.now();
                    }
                    billingService.calculateBilling(session, exit);
                    allocationService.releaseSpot(session.getSpot());
                    session.setClosed(true);
                    repo.save(session);
                });
    }
}
