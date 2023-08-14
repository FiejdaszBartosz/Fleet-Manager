package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.LocationTimer;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.TimeTimer;

public abstract class RideLoop {
    private final RideSession rideSession;
    private final ApplicationContextSingleton appContext;
    protected TimeTimer timeTimer;
    protected LocationTimer locationTimer;

    public RideLoop() {
        this.rideSession = RideSession.getInstance();
        this.appContext = ApplicationContextSingleton.getInstance();
    }

    public RideSession getRideSession() {
        return rideSession;
    }

    public Context getContext() {
        return appContext.getAppContext();
    }

    public abstract void startLoop();
    public abstract void endLoop();
    public TimeTimer getTimeTimer() {return timeTimer;}
}
