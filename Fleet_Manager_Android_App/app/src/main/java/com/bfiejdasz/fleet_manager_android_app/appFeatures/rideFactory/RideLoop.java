package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.LocationTimer;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.TimeTimer;

public abstract class RideLoop {
    private RideSession rideSession;
    private Context context;
    protected TimeTimer timeTimer;
    protected LocationTimer locationTimer;

    public RideLoop() {
        this.rideSession = RideSession.getInstance();
    }

    public RideSession getRideSession() {
        return rideSession;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void startLoop();
    public abstract void endLoop();
    public TimeTimer getTimeTimer() {return timeTimer;};
}
