package com.example.guhao.mytrail.data;

public class Loc {
    private double longitude;
    private double latitude;

    public Loc(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
