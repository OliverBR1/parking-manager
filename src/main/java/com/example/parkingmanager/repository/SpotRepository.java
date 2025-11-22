package com.example.parkingmanager.repository;

import com.example.parkingmanager.entity.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpotRepository extends JpaRepository<SpotEntity, Long> {
    List<SpotEntity> findBySectorAndOccupiedFalse(String sector);
    long countBySectorAndOccupiedTrue(String sector);
    long countBySector(String sector);
}
