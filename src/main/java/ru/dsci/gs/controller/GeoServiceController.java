package ru.dsci.gs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dsci.gs.models.dtos.*;
import ru.dsci.gs.models.entities.GeoPair;
import ru.dsci.gs.models.entities.GeoPoint;
import ru.dsci.gs.services.GeoDataProvider;
import ru.dsci.gs.tools.GeoTools;

import java.io.IOException;

@Slf4j
@ResponseStatus
@RestControllerAdvice
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/gs/api/v1")
public class GeoServiceController {

    private final ObjectMapper objectMapper;
    private final GeoDataProvider geoDataProvider;

    @Operation(
            summary = "Возвращает расстояние между точками",
            description = "Возвращает расстояние между географическими координатами (м)")
    @RequestMapping(
            value = "/distance",
            headers = {"content-type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<BasicValueDto> getDistance(@RequestBody GeoPairDto geoPairDto) {
        try {
            return  ResponseEntity.ok(new BasicValueDto(String.valueOf(GeoTools.getDistance(objectMapper.convertValue(geoPairDto, GeoPair.class)))));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(
            summary = "Возвращает адрес по координатам",
            description = "С помощью операции геокодирования возвращает адрес по координатам")
    @RequestMapping(
            value = "/geodata/bypoint",
            headers = {"content-type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<GeoDataDto> getAddress(@RequestBody GeoPointDto geoPointDto) throws IOException, InterruptedException {
        try {
            return ResponseEntity.ok(objectMapper.convertValue(geoDataProvider.getGeoData(objectMapper.convertValue(geoPointDto, GeoPoint.class)), GeoDataDto.class));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(
            summary = "Возвращает координаты и форматированный адрес по адресу",
            description = "С помощью операции геокодирования возвращает координаты и форматированный адрес по адресу")
    @RequestMapping(
            value = "/geodata/byaddress",
            headers = {"content-type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<GeoDataDto> getGeoPoint(@RequestBody BasicAddressDto address) throws IOException, InterruptedException {
        try {
            return ResponseEntity.ok(objectMapper.convertValue(geoDataProvider.getGeoData(address.getAddress()), GeoDataDto.class));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

}