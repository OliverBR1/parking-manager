package com.example.parkingmanager.service.parking.logic;

import com.example.parkingmanager.entity.ParkingSessionEntity;
import com.example.parkingmanager.repository.ParkingSessionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class RevenueService {

    private final ParkingSessionRepository repo;

    public RevenueService(ParkingSessionRepository repo) {
        this.repo = repo;
    }

    public BigDecimal calculate(String sector, Instant from, Instant to) {
        List<ParkingSessionEntity> sessions = repo.findBySectorAndExitTimeBetween(sector, from, to);
        return sessions.stream()
                .map(ParkingSessionEntity::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
