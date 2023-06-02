package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

public class RideFactorySingleton {
    private static RideFactorySingleton instance = null;
    private IRideFactory rideFactory;

    private RideFactorySingleton() {
    }

    public static RideFactorySingleton getInstance() {
        if (instance == null) {
            instance = new RideFactorySingleton();
        }
        return instance;
    }

    public IRideFactory getRideFactory() {
        return rideFactory;
    }

    public void setRideFactory(IRideFactory rideFactory) {
        this.rideFactory = rideFactory;
    }
}

