package com.example.parkingmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spots")
public class SpotEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String sector;

    private Double lat;
    private Double lng;

    @Column(nullable = false)
    private boolean occupied = false;

    public SpotEntity() {}

    public SpotEntity(Long id, String sector, Double lat, Double lng) {
        this.id = id;
        this.sector = sector;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }
    public Double getLng() { return lng; }
    public void setLng(Double lng) { this.lng = lng; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }
}
