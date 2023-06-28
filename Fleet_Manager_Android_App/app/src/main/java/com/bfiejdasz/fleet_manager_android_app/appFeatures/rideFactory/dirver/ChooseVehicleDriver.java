package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ChooseVehicle;

import java.util.concurrent.CompletableFuture;

public class ChooseVehicleDriver extends ChooseVehicle {

    public ChooseVehicleDriver() {
        super();
    }

    public CompletableFuture<Boolean> isVehicleAvailable(String licencePlate) {
        return super.isVehicleAvailable(licencePlate);
    }

    public CompletableFuture<Boolean> bookVehicle(String licencePlate) {
        return super.bookVehicle(licencePlate);
    }
}
