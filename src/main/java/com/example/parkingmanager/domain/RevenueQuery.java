package com.example.parkingmanager.domain;

import java.time.Instant;

public record RevenueQuery(
        String sector,
        Instant from,
        Instant to
) {}