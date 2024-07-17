package ru.dsci.gs.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "basicAddress", description = "Адрес")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    @Schema(name = "address", description = "Адрес одной строкой")
    private String address;

}
