package com.example.parkingmanager.dto;

import jakarta.validation.constraints.NotBlank;

public record RevenueRequest(
        @NotBlank(message = "Sector is required")
        String sector,

        @NotBlank(message = "Date is required (yyyy-MM-dd)")
        String date
) {}