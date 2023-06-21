package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

public interface IRideFactory {
    RideLoop rideLoop() throws ContextNotSetException;
    ChooseVehicle choseVehicle() throws ContextNotSetException;
    void setContext(Context context);
}
