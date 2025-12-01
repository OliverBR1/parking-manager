package com.example.parkingmanager.core.usecase;

import com.example.parkingmanager.domain.RevenueQuery;
import com.example.parkingmanager.service.parking.logic.RevenueService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
public class RevenueUseCase {

    private final RevenueService service;

    public RevenueUseCase(RevenueService service) {
        this.service = service;
    }

    public BigDecimal calculate(RevenueQuery query) {
        return service.calculate(query.sector(), query.from(), query.to());
    }
}
