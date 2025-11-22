package com.example.parkingmanager.service;

import com.example.parkingmanager.dto.GarageResponse;
import com.example.parkingmanager.entity.SectorEntity;
import com.example.parkingmanager.entity.SpotEntity;
import com.example.parkingmanager.repository.SectorRepository;
import com.example.parkingmanager.repository.SpotRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class GarageSyncService {

    private final WebClient webClient;
    private final SectorRepository sectorRepo;
    private final SpotRepository spotRepo;

    public GarageSyncService(WebClient webClient,
                             SectorRepository sectorRepo,
                             SpotRepository spotRepo) {
        this.webClient = webClient;
        this.sectorRepo = sectorRepo;
        this.spotRepo = spotRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sync() {
        try {
            GarageResponse resp = webClient.get()
                    .uri("/garage")
                    .retrieve()
                    .bodyToMono(GarageResponse.class)
                    .block();

            if (resp == null) return;

            resp.garage().forEach(g -> {
                SectorEntity s = new SectorEntity(g.sector(), BigDecimal.valueOf(g.basePrice()), g.max_capacity());
                sectorRepo.save(s);
            });
            resp.spots().forEach(sp -> {
                SpotEntity spot = new SpotEntity(sp.id(), sp.sector(), sp.lat(), sp.lng());
                spotRepo.save(spot);
            });
            System.out.println("Garage synchronized.");
        } catch (Exception ex) {
            System.err.println("Error syncing garage: " + ex.getMessage());
        }
    }
}
