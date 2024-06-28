package ru.dsci.gs.models.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "basicAddress", description = "Адрес")
@JsonRootName(value = "basicAddress")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicAddressDto {

    @Schema(name = "basicAddress", description = "Адрес одной строкой")
    private String address;

}
