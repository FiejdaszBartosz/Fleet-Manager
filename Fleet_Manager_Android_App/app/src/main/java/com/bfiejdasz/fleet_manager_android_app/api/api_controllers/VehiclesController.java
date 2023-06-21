package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiVehicles;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class VehiclesController {
    private final IApiVehicles apiVehicles;

    public VehiclesController() {
        apiVehicles = ApiClient.getClient().create(IApiVehicles.class);
    }

    public void getVehicles(Callback<List<VehiclesEntity>> callback) {
        Call<List<VehiclesEntity>> call = apiVehicles.getVehicles();
        BaseController<List<VehiclesEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getVehiclesById(int id, Callback<VehiclesEntity> callback) {
        Call<VehiclesEntity> call = apiVehicles.getVehiclesById(id);
        BaseController<VehiclesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deleteVehiclesById(int id, Callback<Void> callback) {
        Call<Void> call = apiVehicles.deleteVehiclesById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createUser(VehiclesEntity vehicle, Callback<VehiclesEntity> callback) {
        Call<VehiclesEntity> call = apiVehicles.createVehicles(vehicle);
        BaseController<VehiclesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updateVehiclesById(int id, VehiclesEntity vehicle, final Callback<VehiclesEntity> callback) {
        Call<VehiclesEntity> call = apiVehicles.updateVehiclesById(id, vehicle);
        BaseController<VehiclesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getVehiclesByLicencePlate(String plate, Callback<VehiclesEntity> callback) {
        Call<VehiclesEntity> call = apiVehicles.getVehicleByLicensePlate(plate);
        BaseController<VehiclesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void isVehicleInUse(String licencePlate, Callback<Short> callback) {
        Call<Short> call = apiVehicles.checkInUse(licencePlate);
        BaseController<Short> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void bookVehicle(String licencePlate, Short inUse, Callback<Void> callback) {
        Call<Void> call = apiVehicles.changeInUseVehicle(licencePlate, inUse);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
