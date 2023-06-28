package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.CheckInList;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ChooseVehicle;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.IRideFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideLoop;

public class DriverFactory implements IRideFactory {
    private Context context;

    @Override
    public RideLoop rideLoop() throws ContextNotSetException {
        if (context == null)
            throw new ContextNotSetException("Driver Factory -> RideLoop - Context not set");
        RideLoopDriver rideLoopDriver = new RideLoopDriver();
        rideLoopDriver.setContext(context);
        return rideLoopDriver;
    }

    @Override
    public ChooseVehicle choseVehicle() throws ContextNotSetException {
        if (context == null)
            throw new ContextNotSetException("Driver Factory -> ChooseVehicle - Context not set");
        ChooseVehicle chooseVehicle = new ChooseVehicleDriver();
        chooseVehicle.setContext(context);
        return chooseVehicle;
    }

    @Override
    public CheckInList checkInList() throws ContextNotSetException {
        if (context == null)
            throw new ContextNotSetException("Driver Factory -> ChooseVehicle - Context not set");
        return new CheckInListDriver();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
