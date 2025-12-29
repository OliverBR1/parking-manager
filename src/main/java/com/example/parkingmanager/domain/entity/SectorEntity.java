package com.example.parkingmanager.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sectors")
public class SectorEntity {
    @Id
    @Column(name = "name", length = 10)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private int maxCapacity;

    public SectorEntity() {
    }

    public SectorEntity(String name, BigDecimal basePrice, int maxCapacity) {
        this.name = name;
        this.basePrice = basePrice;
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
