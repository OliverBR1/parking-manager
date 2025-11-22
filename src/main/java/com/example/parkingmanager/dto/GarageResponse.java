package com.example.parkingmanager.dto;

import java.util.List;

public record GarageResponse(
    List<GarageSector> garage,
    List<GarageSpot> spots
) {
    public record GarageSector(String sector, double basePrice, int max_capacity) {}
    public record GarageSpot(long id, String sector, double lat, double lng) {}
}
