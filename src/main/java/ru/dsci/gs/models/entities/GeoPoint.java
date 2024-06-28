package ru.dsci.gs.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.dsci.gs.tools.Constants;

import java.text.DecimalFormat;

@RequiredArgsConstructor
@Data
public class GeoPoint {

    private final Double lat;

    private final Double lon;

    @JsonIgnore
    private DecimalFormat format = Constants.GEO_FORMAT;

    @Override
    public String toString() {
        return String.format("%s,%s", format.format(lat), format.format(lon));
    }

}
