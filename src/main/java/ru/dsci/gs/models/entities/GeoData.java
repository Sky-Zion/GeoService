package ru.dsci.gs.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GeoData {

    private String basicAddress;

    private GeoPoint geoPoint;

}
