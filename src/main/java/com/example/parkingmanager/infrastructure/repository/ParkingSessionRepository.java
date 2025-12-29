package com.example.parkingmanager.infrastructure.repository;

import com.example.parkingmanager.domain.entity.ParkingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ParkingSessionRepository extends JpaRepository<ParkingSessionEntity, Long> {
    List<ParkingSessionEntity> findByLicensePlateAndClosedFalse(String licensePlate);

    List<ParkingSessionEntity> findBySectorAndExitTimeBetween(String sector, Instant from, Instant to);
}
