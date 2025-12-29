package com.example.parkingmanager.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record GarageData(
        @NotEmpty @Valid List<SectorData> sectors,
        @NotEmpty @Valid List<SpotData> spots
) {}
