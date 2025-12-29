package com.example.parkingmanager.infrastructure.service.parking.logic;

import com.example.parkingmanager.domain.entity.SectorEntity;
import com.example.parkingmanager.domain.entity.SpotEntity;
import com.example.parkingmanager.infrastructure.repository.SectorRepository;
import com.example.parkingmanager.infrastructure.repository.SpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpotAllocationService {

    private final SpotRepository spotRepo;
    private final SectorRepository sectorRepo;

    public SpotAllocationService(SpotRepository spotRepo, SectorRepository sectorRepo) {
        this.spotRepo = spotRepo;
        this.sectorRepo = sectorRepo;
    }

    public record AllocationResult(boolean success, SectorEntity sector, SpotEntity spot) {
    }

    @Transactional
    public AllocationResult allocateSpot() {
        List<SectorEntity> sectors = sectorRepo.findAll();
        for (SectorEntity sector : sectors) {
            long occupied = spotRepo.countBySectorAndOccupiedTrue(sector.getName());
            long total = sector.getMaxCapacity();
            if (total == 0) continue;
            if (occupied >= total) continue;
            var free = spotRepo.findBySectorAndOccupiedFalse(sector.getName());
            if (free.isEmpty()) continue;
            SpotEntity spot = free.get(0);
            spot.setOccupied(true);
            spotRepo.save(spot);
            return new AllocationResult(true, sector, spot);
        }
        return new AllocationResult(false, null, null);
    }

    @Transactional
    public void releaseSpot(SpotEntity spot) {
        if (spot == null) return;
        spot.setOccupied(false);
        spotRepo.save(spot);
    }
}
