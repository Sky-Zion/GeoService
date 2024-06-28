package ru.dsci.gs.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "geoData", description = "Адрес и координаты объекта")
@JsonRootName(value = "geoData")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoDataDto {

    private GeoPointDto geoPoint;

    @JsonProperty("basicAddress")
    private BasicAddressDto basicAddress;

}
