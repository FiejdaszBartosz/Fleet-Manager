package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRides;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RidesController {
    private final IApiRides apiRides;

    public RidesController() {
        apiRides = ApiClient.getClient().create(IApiRides.class);
    }

    public void getRides(Callback<List<RidesEntity>> callback) {
        Call<List<RidesEntity>> call = apiRides.getRides();
        BaseController<List<RidesEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getRidesById(int id, Callback<RidesEntity> callback) {
        Call<RidesEntity> call = apiRides.getRideById(id);
        BaseController<RidesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deleteRideById(int id, Callback<Void> callback) {
        Call<Void> call = apiRides.deleteRideById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createRide(RidesEntity ride, Callback<RidesEntity> callback) {
        Call<RidesEntity> call = apiRides.createRide(ride);
        BaseController<RidesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updateRideById(long id, RidesEntity ride, final Callback<RidesEntity> callback) {
        Call<RidesEntity> call = apiRides.updateRideById(id, ride);
        BaseController<RidesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
