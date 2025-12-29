package com.example.parkingmanager.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebhookEventDto(
        @JsonProperty("license_plate") String licensePlate,
        @JsonProperty("entry_time") String entryTime,
        @JsonProperty("exit_time") String exitTime,
        @JsonProperty("lat") Double lat,
        @JsonProperty("lng") Double lng,
        @JsonProperty("event_type") String eventType
) {
}
