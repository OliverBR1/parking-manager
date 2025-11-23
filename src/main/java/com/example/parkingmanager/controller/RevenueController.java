package com.example.parkingmanager.controller;

import com.example.parkingmanager.service.parking.logic.RevenueService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.*;

@RestController
public class RevenueController {

    private final RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping("/revenue")
    public ResponseEntity<?> revenue(
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody(required = false) RevenueRequest body
    ) {
        if (body != null) {
            sector = body.sector;
            if (body.date != null && date == null) date = LocalDate.parse(body.date);
        }
        if (sector == null || date == null) {
            return ResponseEntity.badRequest().body("Required: sector and date (yyyy-MM-dd)");
        }
        Instant from = date.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant to = date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        BigDecimal amount = revenueService.calculate(sector, from, to);
        var resp = new RevenueResponse(amount.setScale(2, BigDecimal.ROUND_HALF_UP), "BRL", Instant.now());
        return ResponseEntity.ok(resp);
    }

    public static class RevenueRequest {
        public String date;
        public String sector;
    }

    public static record RevenueResponse(BigDecimal amount, String currency, Instant timestamp) {
    }
}
