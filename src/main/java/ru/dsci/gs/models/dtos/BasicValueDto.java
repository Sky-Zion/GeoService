package ru.dsci.gs.models.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "basicValue", description = "Значение базового типа (int, boolean, date и т.п.)")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicValueDto {

    @Schema(name = "value", example = "true", description = "Значение")
    private String value;

}
