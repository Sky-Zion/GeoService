package ru.dsci.gs.tools;

import ru.dsci.gs.models.entities.GeoPair;
import ru.dsci.gs.models.entities.GeoPoint;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GeoTools {

    public static final double EARTH_R = 6378137;

    public static double getDistance(GeoPair geoPair) {
        return getDistance(geoPair.getPointA(), geoPair.getPointB());
    }

    public static double getDistance(GeoPoint geoPointA, GeoPoint geoPointB) {
        return getDistance(geoPointA.getLat(), geoPointA.getLon(), geoPointB.getLat(), geoPointB.getLon());
    }

    public static double getDistance(double latA, double lonA, double latB, double lonB) {
        double radLatA = Math.toRadians(latA);
        double radLatB = Math.toRadians(latB);
        double radLonA = Math.toRadians(lonA);
        double radLonB = Math.toRadians(lonB);
        return Math.acos(sin(radLatA) * sin(radLatB) + cos(radLatA) * cos(radLatB) * cos(radLonB - radLonA)) * EARTH_R;
    }

    public static double latOffsetRad(double dX) {
        return dX / EARTH_R;
    }

    public static double lonOffsetToRad(double dY, double lat) {
        return dY / (EARTH_R * cos(lat));
    }

    public static double latOffsetToDeg(double dX) {
        return Math.toDegrees(latOffsetRad(dX));
    }

    public static double lonOffsetToDeg(double dY, double lat) {
        return Math.toDegrees(lonOffsetToRad(dY, Math.toRadians(lat)));
    }

    public static GeoPoint pointOffset(GeoPoint geoPoint, double dX, double dY) {
        double lat = geoPoint.getLat() + latOffsetToDeg(dX);
        return new GeoPoint(lat, (geoPoint.getLon() + lonOffsetToDeg(dY, lat)));
    }

}
