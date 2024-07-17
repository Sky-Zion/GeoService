package ru.dsci.gs.models.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "geoPair", description = "Пара координат")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoPairDto {

    private GeoPointDto pointA;

    private GeoPointDto pointB;

}
