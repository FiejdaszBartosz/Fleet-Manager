package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

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
    public void setContext(Context context) {
        this.context = context;
    }
}
