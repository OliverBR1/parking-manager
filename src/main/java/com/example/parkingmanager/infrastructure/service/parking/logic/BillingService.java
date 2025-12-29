package com.example.parkingmanager.infrastructure.service.parking.logic;

import com.example.parkingmanager.domain.entity.ParkingSessionEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;

@Service
public class BillingService {

    public void calculateBilling(ParkingSessionEntity session, Instant exitTime) {
        session.setExitTime(exitTime);
        long minutes = Duration.between(session.getEntryTime(), exitTime).toMinutes();
        if (minutes <= 30) {
            session.setTotalAmount(BigDecimal.ZERO);
            return;
        }
        long billable = minutes - 30;
        long hours = (long) Math.ceil(billable / 60.0);
        BigDecimal amount = session.getPricePerHour().multiply(BigDecimal.valueOf(hours));
        session.setTotalAmount(amount.setScale(2, RoundingMode.HALF_UP));
    }
}
