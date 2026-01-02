package com.example.parkingmanager.infrastructure.service.parking.handler;

import com.example.parkingmanager.application.dto.WebhookEventDto;
import com.example.parkingmanager.application.gateway.input.WebhookEventHandler;
import com.example.parkingmanager.domain.entity.ParkingSessionEntity;
import com.example.parkingmanager.domain.entity.SectorEntity;
import com.example.parkingmanager.domain.entity.SpotEntity;
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

        SpotAllocationService.AllocationResult allocationResult = allocationService.allocateSpot();
        if (!allocationResult.success()) {
            return;
        }

        SectorEntity allocatedSector = allocationResult.sector();
        SpotEntity allocatedSpot = allocationResult.spot();

        BigDecimal hourlyPrice = pricingService.calculatePrice(allocatedSector);

        String licensePlate = dto.licensePlate();
        String entryTimestampRaw = dto.entryTime();

        Instant entryTimestamp = parseEntryTimestamp(entryTimestampRaw);

        ParkingSessionEntity session = new ParkingSessionEntity();
        session.setLicensePlate(licensePlate);
        session.setSector(allocatedSector.getName());
        session.setSpot(allocatedSpot);
        session.setEntryTime(entryTimestamp);
        session.setPricePerHour(hourlyPrice);

        sessionRepo.save(session);
    }

    private Instant parseEntryTimestamp(String timestamp) {
        try {
            return Instant.parse(timestamp);
        } catch (DateTimeParseException e) {
            return Instant.now();
        }
    }
}
