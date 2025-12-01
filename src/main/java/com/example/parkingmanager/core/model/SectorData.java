package com.example.parkingmanager.core.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SectorData(
        @NotNull String name,
        @NotNull BigDecimal basePrice,
        @Min(0) int capacity
) {}
