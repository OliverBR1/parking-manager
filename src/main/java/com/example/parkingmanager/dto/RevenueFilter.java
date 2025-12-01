package com.example.parkingmanager.dto;

import jakarta.validation.constraints.NotBlank;

public record RevenueFilter(
        @NotBlank(message = "sector is required")
        String sector,

        @NotBlank(message = "date is required and must be yyyy-MM-dd")
        String date
) {}
