package com.example.parkingmanager.service.parking.logic;

import com.example.parkingmanager.core.usecase.RevenueMapper;
import com.example.parkingmanager.core.usecase.RevenueUseCase;
import com.example.parkingmanager.domain.RevenueQuery;
import com.example.parkingmanager.dto.RevenueFilter;
import com.example.parkingmanager.dto.RevenueResponse;
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