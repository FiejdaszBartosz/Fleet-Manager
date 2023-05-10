package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.os.Handler;

import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;

import java.sql.Timestamp;

public class RideSession {
    private static RideSession instance = null;
    private RidesEntity ride;
    private long currentTime;
    private Handler handler;
    private Runnable runnable;

    private RideSession() {
        this.ride = new RidesEntity();
        this.handler = new Handler();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                currentTime = System.currentTimeMillis() - ride.getStartTime().getTime();
                handler.postDelayed(this, 1000);
            }
        };
    }

    public static RideSession getInstance() {
        if (instance == null)
            instance = new RideSession();

        return instance;
    }

    public RidesEntity getRide() {
        return ride;
    }

    public void setRide(RidesEntity ride) {
        this.ride = ride;
    }

    public void startTimer() {
        ride.setStartTime(new Timestamp(System.currentTimeMillis()));
        handler.post(runnable);
    }

    public void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    public long getCurrentTime() {
        return currentTime;
    }
}