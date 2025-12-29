package com.example.parkingmanager.infrastructure.service.parking.logic;

import com.example.parkingmanager.application.usecase.RevenueMapper;
import com.example.parkingmanager.application.gateway.input.RevenueQuery;
import com.example.parkingmanager.application.dto.RevenueFilter;
import com.example.parkingmanager.application.dto.RevenueResponse;
import com.example.parkingmanager.application.usecase.RevenueUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class RevenueFacade {

    private final RevenueMapper mapper;
    private final RevenueUseCase useCase;

    public RevenueFacade(RevenueMapper mapper, RevenueUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    public RevenueResponse execute(RevenueFilter filter) {

        RevenueQuery query = mapper.toQuery(filter);

        BigDecimal amount = useCase.calculate(query);

        return new RevenueResponse(
                amount,
                "BRL",
                Instant.now()
        );
    }
}