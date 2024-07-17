package ru.dsci.gs.models.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "areaDto", description = "Прямоугольная область")
@Data
public class AreaDto {

    private GeoPointDto center;
    private double width;
    private double height;

}
