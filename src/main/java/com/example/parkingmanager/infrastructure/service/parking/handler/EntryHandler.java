package com.example.parkingmanager.infrastructure.service.parking.handler;

import com.example.parkingmanager.application.dto.WebhookEventDto;
import com.example.parkingmanager.application.gateway.input.WebhookEventHandler;
import com.example.parkingmanager.domain.entity.ParkingSessionEntity;
import com.example.parkingmanager.infrastructure.service.parking.logic.DynamicPricingService;
import com.example.parkingmanager.infrastructure.service.parking.logic.SpotAllocationService;
import com.example.parkingmanager.infrastructure.repository.ParkingSessionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeParseException;

@Service
public class EntryHandler implements WebhookEventHandler {

    private final SpotAllocationService allocationService;
    private final DynamicPricingService pricingService;
    private final ParkingSessionRepository sessionRepo;

    public EntryHandler(SpotAllocationService allocationService,
                        DynamicPricingService pricingService,
                        ParkingSessionRepository sessionRepo) {
        this.allocationService = allocationService;
        this.pricingService = pricingService;
        this.sessionRepo = sessionRepo;
    }

    @Override
    public boolean supports(String eventType) {
        return "ENTRY".equalsIgnoreCase(eventType);
    }

    @Override
    public void handle(WebhookEventDto dto) {
        var res = allocationService.allocateSpot();
        if (!res.success()) {
            return;
        }
        var sector = res.sector();
        var spot = res.spot();

        BigDecimal price = pricingService.calculatePrice(sector);

        ParkingSessionEntity session = new ParkingSessionEntity();
        session.setLicensePlate(dto.licensePlate());
        session.setSector(sector.getName());
        session.setSpot(spot);
        try {
            session.setEntryTime(Instant.parse(dto.entryTime()));
        } catch (DateTimeParseException e) {
            session.setEntryTime(Instant.now());
        }
        session.setPricePerHour(price);
        sessionRepo.save(session);
    }
}
