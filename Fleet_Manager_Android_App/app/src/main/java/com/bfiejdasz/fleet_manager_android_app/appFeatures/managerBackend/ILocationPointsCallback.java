package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public interface ILocationPointsCallback {
    void onPointsDownloaded(List<GeoPoint> points);
}

