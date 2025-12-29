package com.example.parkingmanager.infrastructure.adapter;

import com.example.parkingmanager.domain.model.SectorData;
import com.example.parkingmanager.domain.model.SpotData;
import com.example.parkingmanager.application.gateway.output.GarageSyncGateway;
import com.example.parkingmanager.domain.entity.SectorEntity;
import com.example.parkingmanager.domain.entity.SpotEntity;
import com.example.parkingmanager.infrastructure.repository.SectorRepository;
import com.example.parkingmanager.infrastructure.repository.SpotRepository;
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
