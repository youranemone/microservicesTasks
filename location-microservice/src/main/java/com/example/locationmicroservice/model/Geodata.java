package com.example.locationmicroservice.model;

import jakarta.persistence.*;

import lombok.NonNull;



@Entity
public class Geodata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull private double lon;
    @NonNull private double lat;
    @NonNull private String name;

    public Geodata(@NonNull double lon, @NonNull double lat, @NonNull String name) {
        this.lon = lon;
        this.lat = lat;
        this.name = name;
    }

    public Geodata() {
    }

    public int getId() {
        return id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
