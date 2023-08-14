package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;


import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.AddVehicleParameter;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ParameterNotSetError;

import java.util.concurrent.CompletableFuture;

public class AddVehicleParameterDriver extends AddVehicleParameter {
    public AddVehicleParameterDriver() {
        super();
    }

    public void addStartKm(int km) {
        rideSession.getRide().setStartKm(km);
    }

    public void addStartFuel(int fuel) {
        rideSession.getRide().setStartFuel(fuel);
    }

    public CompletableFuture<Boolean>  updateParameters() throws ParameterNotSetError {
        RidesEntity temp = rideSession.getRide();
        if (temp.getStartFuel() != null && temp.getStartKm() != null) {
            return super.updateRideParameters();
        }
        else
            throw new ParameterNotSetError("updateParameters fuel or start km wasnt set");
    }
}
