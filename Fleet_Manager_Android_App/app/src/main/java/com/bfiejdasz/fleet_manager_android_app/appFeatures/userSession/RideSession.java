package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;

public class RideSession {
    private static RideSession instance = null;
    private RidesEntity ride;

    private RideSession() {
        this.ride = new RidesEntity();
    }

    public static RideSession getInstance() {
        if (instance == null)
            instance = new RideSession();

        return instance;
    }

    public RidesEntity getRide() {
        return ride;
    }

    public void setRide(RidesEntity ride) {
        this.ride = ride;
    }
}
