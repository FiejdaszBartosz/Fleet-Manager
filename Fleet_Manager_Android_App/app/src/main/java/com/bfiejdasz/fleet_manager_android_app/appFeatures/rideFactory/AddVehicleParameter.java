package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class AddVehicleParameter {
    protected RideSession rideSession;
    protected Context context;

    public AddVehicleParameter() {
        this.rideSession = RideSession.getInstance();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CompletableFuture<Boolean> updateRideParameters() {
        RidesEntity temp = rideSession.getRide();
        CompletableFuture<Boolean> futureResult = new CompletableFuture<>();
        rideSession.getRidesController().updateRideById(temp.getRideId(), temp, new Callback<RidesEntity>() {
            @Override
            public void onResponse(Call<RidesEntity> call, Response<RidesEntity> response) {
                if (response.isSuccessful()) {
                    futureResult.complete(true);
                } else {
                    futureResult.complete(false);
                }
            }

            @Override
            public void onFailure(Call<RidesEntity> call, Throwable t) {
                try {
                    ErrorHandler.handleException(t);
                } catch (Exception e) {
                    ErrorHandler.logWithToastErrors(context, e);
                }
                futureResult.complete(false);
            }
        });
        return futureResult;
    }
}
