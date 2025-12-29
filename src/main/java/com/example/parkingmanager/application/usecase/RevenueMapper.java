package com.example.parkingmanager.application.usecase;

import com.example.parkingmanager.application.gateway.input.RevenueQuery;
import com.example.parkingmanager.application.dto.RevenueFilter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Component
public class RevenueMapper {

    public RevenueQuery toQuery(RevenueFilter filter) {

        LocalDate date = LocalDate.parse(filter.date());

        Instant from = date.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant to = date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        return new RevenueQuery(
                filter.sector(),
                from,
                to
        );
    }
}
