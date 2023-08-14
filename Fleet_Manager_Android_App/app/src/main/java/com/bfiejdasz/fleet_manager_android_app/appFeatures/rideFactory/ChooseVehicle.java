package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.VehiclesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.UserSession;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ChooseVehicle {
    protected UserSession userSession;
    protected RideSession rideSession;
    protected VehiclesController vehiclesController;
    protected ApplicationContextSingleton appContext;


    protected ChooseVehicle() {
        this.userSession = UserSession.getInstance();
        this.rideSession = RideSession.getInstance();
        this.vehiclesController = new VehiclesController();
        this.appContext = ApplicationContextSingleton.getInstance();
    }

    public Context getContext() {
        return appContext.getAppContext();
    }

    public CompletableFuture<Boolean> bookVehicle(String licencePlate) {
        return isVehicleAvailable(licencePlate)
                .thenComposeAsync(isAvailable -> {
                    if (isAvailable) {
                        CompletableFuture<Boolean> futureResult = new CompletableFuture<>();
                        vehiclesController.bookVehicle(licencePlate, (short) 1, new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    futureResult.complete(true);
                                } else {
                                    futureResult.complete(false);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                try {
                                    ErrorHandler.handleException(t);
                                } catch (Exception e) {
                                    ErrorHandler.logWithToastErrors(appContext.getAppContext(), e);
                                }
                                futureResult.complete(false);
                            }
                        });
                        return futureResult;
                    } else {
                        return CompletableFuture.completedFuture(false);
                    }
                });
    }


    public CompletableFuture<Boolean> isVehicleAvailable(String licencePlate) {
        CompletableFuture<Boolean> futureResult = new CompletableFuture<>();

        vehiclesController.isVehicleInUse(licencePlate, new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                boolean isAvailable = response.body() != 1;
                futureResult.complete(isAvailable);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                futureResult.completeExceptionally(t);
            }
        });

        return futureResult;
    }

    public CompletableFuture<VehiclesEntity> getVehicleByPlate(String plate) {
        CompletableFuture<VehiclesEntity> futureResult = new CompletableFuture<>();

        vehiclesController.getVehiclesByLicencePlate(plate, new Callback<VehiclesEntity>() {
            @Override
            public void onResponse(Call<VehiclesEntity> call, Response<VehiclesEntity> response) {
                futureResult.complete(response.body());
            }

            @Override
            public void onFailure(Call<VehiclesEntity> call, Throwable t) {
                futureResult.completeExceptionally(t);
            }
        });

        return futureResult;
    }
}
