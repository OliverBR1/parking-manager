package com.example.parkingmanager.application.gateway.output;

import com.example.parkingmanager.domain.model.SectorData;
import com.example.parkingmanager.domain.model.SpotData;

import java.util.List;

public interface GarageSyncGateway {
    void saveSectors(List<SectorData> sectors);
    void saveSpots(List<SpotData> spots);
}
