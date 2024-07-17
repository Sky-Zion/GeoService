package ru.dsci.gs.models.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "geoPoint", description = "Географические координаты точки")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoPointDto {

    @Schema(name = "lat", example = "50.595402", description = "Широта")
    private Double lat;
    @Schema(name = "lon", example = "36.587256", description = "Долгота")
    private Double lon;

}
