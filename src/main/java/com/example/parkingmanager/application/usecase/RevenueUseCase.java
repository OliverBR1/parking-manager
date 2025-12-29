package com.example.parkingmanager.application.usecase;

import com.example.parkingmanager.application.gateway.input.RevenueQuery;
import com.example.parkingmanager.infrastructure.service.parking.logic.RevenueService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
