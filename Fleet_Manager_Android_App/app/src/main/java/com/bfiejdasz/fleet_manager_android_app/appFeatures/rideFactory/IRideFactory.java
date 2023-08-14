package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;

public interface IRideFactory {
    RideLoop rideLoop() throws ContextNotSetException;
    ChooseVehicle choseVehicle() throws ContextNotSetException;
    CheckInList checkInList() throws ContextNotSetException;
    AddVehicleParameter addVehicleParameter() throws ContextNotSetException;
}
