package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiEmployees;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiPositions;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PositionsController {
    private final IApiPositions apiPositions;

    public PositionsController() {
        apiPositions = ApiClient.getClient().create(IApiPositions.class);
    }

    public void getPositions(Callback<List<PositionsEntity>> callback) {
        Call<List<PositionsEntity>> call = apiPositions.getPositions();
        BaseController<List<PositionsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getPositionsById(int id, Callback<PositionsEntity> callback) {
        Call<PositionsEntity> call = apiPositions.getPositionById(id);
        BaseController<PositionsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getPositionsByRideId(int id, Callback<List<PositionsEntity>> callback) {
        Call<List<PositionsEntity>> call = apiPositions.getPositionsByRideId(id);
        BaseController<List<PositionsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deletePositionsById(int id, Callback<Void> callback) {
        Call<Void> call = apiPositions.deletePositionById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createPosition(PositionsEntity position, Callback<PositionsEntity> callback) {
        Call<PositionsEntity> call = apiPositions.createPosition(position);
        BaseController<PositionsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updatePositionsById(int id, PositionsEntity position, final Callback<PositionsEntity> callback) {
        Call<PositionsEntity> call = apiPositions.updatePositionById(id, position);
        BaseController<PositionsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
