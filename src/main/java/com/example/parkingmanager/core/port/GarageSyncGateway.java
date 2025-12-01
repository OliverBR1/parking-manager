package com.example.parkingmanager.core.port;

import com.example.parkingmanager.core.model.SectorData;
import com.example.parkingmanager.core.model.SpotData;

import java.util.List;

public interface GarageSyncGateway {
    void saveSectors(List<SectorData> sectors);
    void saveSpots(List<SpotData> spots);
}
