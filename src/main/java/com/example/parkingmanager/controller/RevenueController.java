package com.example.parkingmanager.controller;

import com.example.parkingmanager.dto.RevenueFilter;
import com.example.parkingmanager.dto.RevenueResponse;
import com.example.parkingmanager.service.parking.logic.RevenueFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    private final RevenueFacade revenueFacade;

    public RevenueController(RevenueFacade revenueFacade) {
        this.revenueFacade = revenueFacade;
    }

    @GetMapping
    public ResponseEntity<RevenueResponse> getRevenue(@Valid @RequestBody RevenueFilter request) {

        RevenueResponse mock = new RevenueResponse(
                new BigDecimal("0.00"),
                "BRL",
                Instant.parse("2025-01-01T12:00:00.000Z")
        );

        return ResponseEntity.ok(mock);
    }

}