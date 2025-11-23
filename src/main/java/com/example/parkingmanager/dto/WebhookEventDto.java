package com.example.parkingmanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WebhookEventDto(

        @JsonProperty("license_plate")
        String licensePlate,

        @JsonProperty("entry_time")
        String entryTime,

        @JsonProperty("exit_time")
        String exitTime,

        @JsonProperty("lat")
        Double lat,

        @JsonProperty("lng")
        Double lng,

        @JsonProperty("event_type")
        String eventType
) {
}
