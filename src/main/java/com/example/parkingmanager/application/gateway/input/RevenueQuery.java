package com.example.parkingmanager.application.gateway.input;

import java.time.Instant;

public record RevenueQuery(
        String sector,
        Instant from,
        Instant to
) {}