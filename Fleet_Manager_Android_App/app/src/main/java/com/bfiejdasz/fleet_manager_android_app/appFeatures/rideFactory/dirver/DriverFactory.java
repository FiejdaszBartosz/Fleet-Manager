package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.AddVehicleParameter;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.CheckInList;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ChooseVehicle;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.IRideFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideLoop;

public class DriverFactory implements IRideFactory {
    @Override
    public RideLoop rideLoop() {
        return new RideLoopDriver();
    }

    @Override
    public ChooseVehicle choseVehicle() {
        return new ChooseVehicleDriver();
    }

    @Override
    public CheckInList checkInList() {
        return new CheckInListDriver();
    }

    @Override
    public AddVehicleParameter addVehicleParameter() {
        return new AddVehicleParameterDriver();
    }
}
