package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;

import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideLoop;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.LocationTimer;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.TimeTimer;
import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

import java.util.Timer;
import java.util.TimerTask;

public class RideLoopDriver extends RideLoop {
    public RideLoopDriver() {
        super();
    }

    @Override
    public void startLoop() {
        setTimeTimer();
        setLocationTimer();
        timeTimer.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                locationTimer.start();
            }
        }, 5000);

    }

    @Override
    public void endLoop() {
        timeTimer.stop();
        locationTimer.stop();
    }

    private void setTimeTimer() {
        timeTimer = new TimeTimer(System.currentTimeMillis());
    }

    private void setLocationTimer() {
        LocationProviderProxy locationProviderProxy = new LocationProviderProxy(getContext());
        locationTimer = new LocationTimer(getRideSession().getRide(), locationProviderProxy, 25000);
    }
}
