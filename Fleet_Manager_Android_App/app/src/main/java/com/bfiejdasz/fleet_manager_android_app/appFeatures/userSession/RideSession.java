package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.util.Log;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesEmployeesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<Boolean> setRide() {
        CompletableFuture<Boolean> futureResult = new CompletableFuture<>();
        ride.setStartTime(String.valueOf(LocalDateTime.now()));
        ride.createRideId();

        ridesController.createRide(ride, new Callback<RidesEntity>() {
            @Override
            public void onResponse(Call<RidesEntity> call, Response<RidesEntity> response) {
                if (!response.isSuccessful()) {
                    Log.e("EXCEPTION", "RideSession -> setRide");
                    futureResult.complete(false);
                } else {
                    ride = response.body();
                    futureResult.complete(true);
                }
            }

            @Override
            public void onFailure(Call<RidesEntity> call, Throwable t) {
                try {
                    ErrorHandler.handleException(t);
                } catch (Exception e) {
                    ErrorHandler.logErrors(e);
                    futureResult.complete(false);
                }
            }
        });

        return futureResult;
    }

    public RidesController getRidesController() {
        return ridesController;
    }

    public void updateRide() {
        ridesController.updateRideById(ride.getRideId(), ride, new Callback<RidesEntity>() {
            @Override
            public void onResponse(Call<RidesEntity> call, Response<RidesEntity> response) {
                // do nothing
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
}
