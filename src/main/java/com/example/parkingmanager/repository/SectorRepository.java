package com.example.parkingmanager.repository;

import com.example.parkingmanager.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<SectorEntity, String> {
}
