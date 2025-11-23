package com.example.parkingmanager.service.parking.logic;

import com.example.parkingmanager.entity.SectorEntity;
import com.example.parkingmanager.repository.SpotRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DynamicPricingService {

    private final SpotRepository spotRepo;

    public DynamicPricingService(SpotRepository spotRepo) {
        this.spotRepo = spotRepo;
    }

    public BigDecimal calculatePrice(SectorEntity sector) {
        long occupied = spotRepo.countBySectorAndOccupiedTrue(sector.getName());
        long total = sector.getMaxCapacity();
        double ratio = total == 0 ? 0.0 : (double) occupied / (double) total;
        BigDecimal base = sector.getBasePrice();

        if (ratio < 0.25) {
            base = base.multiply(BigDecimal.valueOf(0.90));
        } else if (ratio <= 0.5) {
            base = base;
        } else if (ratio <= 0.75) {
            base = base.multiply(BigDecimal.valueOf(1.10));
        } else {
            base = base.multiply(BigDecimal.valueOf(1.25));
        }

        return base.setScale(0, RoundingMode.CEILING);
    }
}
