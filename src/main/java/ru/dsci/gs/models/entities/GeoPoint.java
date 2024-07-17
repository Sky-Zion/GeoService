package ru.dsci.gs.models.entities;

import lombok.Data;

@Data
public class GeoPoint {

    private final Double lat;
    private final Double lon;

    public GeoPoint(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
