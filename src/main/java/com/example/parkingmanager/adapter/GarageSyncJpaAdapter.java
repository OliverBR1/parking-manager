package com.example.parkingmanager.adapter;

import com.example.parkingmanager.core.model.SectorData;
import com.example.parkingmanager.core.model.SpotData;
import com.example.parkingmanager.core.port.GarageSyncGateway;
import com.example.parkingmanager.entity.SectorEntity;
import com.example.parkingmanager.entity.SpotEntity;
import com.example.parkingmanager.repository.SectorRepository;
import com.example.parkingmanager.repository.SpotRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GarageSyncJpaAdapter implements GarageSyncGateway {

    private final SectorRepository sectorRepo;
    private final SpotRepository spotRepo;

    public GarageSyncJpaAdapter(SectorRepository sectorRepo, SpotRepository spotRepo) {
        this.sectorRepo = sectorRepo;
        this.spotRepo = spotRepo;
    }

    @Override
    public void saveSectors(List<SectorData> sectors) {
        sectors.stream()
                .map(s -> new SectorEntity(s.name(), s.basePrice(), s.capacity()))
                .forEach(sectorRepo::save);
    }

    @Override
    public void saveSpots(List<SpotData> spots) {
        spots.stream()
                .map(s -> new SpotEntity(s.id(), s.sector(), s.lat(), s.lng()))
                .forEach(spotRepo::save);
    }
}
