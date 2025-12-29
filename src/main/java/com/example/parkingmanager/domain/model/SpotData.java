package com.example.parkingmanager.domain.model;

import jakarta.validation.constraints.NotNull;

public record SpotData(
        long id,
        @NotNull String sector,
        double lat,
        double lng
) {}
