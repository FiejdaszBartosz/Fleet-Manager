package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.util.Log;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideSession {
    private static RideSession instance = null;
    private RidesEntity ride;
    private RidesController ridesController;

    private RideSession() {
        this.ride = new RidesEntity();
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

    public void setRide() {
        ride.setStartTime(String.valueOf(LocalDateTime.now()));
        ride.createRideId();

        ridesController.createRide(ride, new Callback<RidesEntity>() {
            @Override
            public void onResponse(Call<RidesEntity> call, Response<RidesEntity> response) {
                if (!response.isSuccessful()) {
                    Log.e("EXCEPTION", "RideSession -> setRide");
                }
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

    public RidesController getRidesController() {
        return ridesController;
    }
}
