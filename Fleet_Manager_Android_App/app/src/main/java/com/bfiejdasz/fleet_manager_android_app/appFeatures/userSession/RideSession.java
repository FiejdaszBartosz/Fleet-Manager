package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Handler;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.TimestampGenerator;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.LocationTimer;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers.TimeTimer;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;
import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideSession {
    private static RideSession instance = null;
    private RidesEntity ride;
    private UserSession userSession;
    public TimeTimer timeTimer;
    public LocationTimer locationTimer;
    private RidesController ridesController;
    private Context context;

    private RideSession() {
        this.ride = new RidesEntity();
        this.userSession = UserSession.getInstance();
        this.ridesController = new RidesController();
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

    private void setRide() {
        ride.setStartTime(String.valueOf(LocalDateTime.now()));
        ride.createRideId();
        ridesController.createRide(ride, new Callback<RidesEntity>() {
            @Override
            public void onResponse(Call<RidesEntity> call, Response<RidesEntity> response) {

            }

            @Override
            public void onFailure(Call<RidesEntity> call, Throwable t) {
                try {
                    ErrorHandler.handleException(t);
                } catch (Exception e) {
                    ErrorHandler.logErrors(e);
                }
            }
        });
    }

    private void setTimeTimer() {
        timeTimer = new TimeTimer(System.currentTimeMillis());
    }

    private void setLocationTimer() {
        LocationProviderProxy locationProviderProxy = new LocationProviderProxy(context);
        locationTimer = new LocationTimer(ride, locationProviderProxy, 25000);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setAll() {
        setRide();
        setTimeTimer();
        setLocationTimer();
    }
}