package com.example.parkingmanager.infrastructure.repository;

import com.example.parkingmanager.domain.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<SectorEntity, String> {
}
