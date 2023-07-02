package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;

import android.util.Log;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.VehiclesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.EndRide;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ParameterNotSetError;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndRideDriver extends EndRide {
    private VehiclesController vehiclesController;

    public EndRideDriver() {
        super();
        vehiclesController = new VehiclesController();
    }

    public void addStopKm(int km) {
        rideSession.getRide().setStopKm(km);
    }

    public void addStopFuel(int fuel) {
        rideSession.getRide().setStopFuel(fuel);
    }

    public void updateParameters() throws ParameterNotSetError {
        RidesEntity temp = rideSession.getRide();
        if (temp.getStopFuel() != null && temp.getStopKm() != null && temp.getStopTime() != null) {
            CompletableFuture<Boolean> futureResult = super.updateRideParameters();
            futureResult.thenAcceptAsync(success -> {
                if (!success)
                    Log.e("EXCEPTION", "updateParameters");
            });
        }
        else
            throw new ParameterNotSetError("updateParameters fuel or start km or stop time wasnt set");
    }

    public void setStopTime() {
        rideSession.getRide().setStopTime(String.valueOf(LocalDateTime.now()));
    }

    public void freeVehicle() {
        vehiclesController.bookVehicle(rideSession.getRide().getVehiclesByVehicle().getLicensePlate(), (short) 0, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("EXCEPTION", "freeVehicle");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("EXCEPTION", "freeVehicle");
            }
        });
    }

    public void updateVehicle(int km) {
        VehiclesEntity temp = rideSession.getRide().getVehiclesByVehicle();
        temp.setMileage(km);

        vehiclesController.updateVehiclesById((int) temp.getIdVehicles(), temp, new Callback<VehiclesEntity>() {
            @Override
            public void onResponse(Call<VehiclesEntity> call, Response<VehiclesEntity> response) {
                if (!response.isSuccessful()) {
                    Log.e("EXCEPTION", "updateVehicle");
                }
            }

            @Override
            public void onFailure(Call<VehiclesEntity> call, Throwable t) {
                Log.e("EXCEPTION", "updateVehicle");
            }
        });
    }
}
