package com.bfiejdasz.fleet_manager_android_app.locationsFeatures;

import android.content.Context;
import android.location.Location;

public class LocationProviderProxy implements ILocationProvider {
    private final LocationProvider locationProvider;

    public LocationProviderProxy(Context context) {
        this.locationProvider = new LocationProvider(context);
    }

    @Override
    public Location getCurrentLocation() {
        locationProvider.askForPermissions();
        return locationProvider.getCurrentLocation();
    }
}