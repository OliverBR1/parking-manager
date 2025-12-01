package com.example.parkingmanager.core.usecase;

import com.example.parkingmanager.core.model.GarageData;
import com.example.parkingmanager.core.port.GarageSyncGateway;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SyncGarageUseCase {

    private final GarageSyncGateway gateway;

    public SyncGarageUseCase(GarageSyncGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(@NotNull @Valid GarageData data) {
        gateway.saveSectors(data.sectors());
        gateway.saveSpots(data.spots());
    }
}
