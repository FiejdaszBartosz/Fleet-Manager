package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiPositions;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRepairs;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RepairsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RepairsController {
    private final IApiRepairs apiRepairs;

    public RepairsController() {
        apiRepairs = ApiClient.getClient().create(IApiRepairs.class);
    }

    public void getRepairs(Callback<List<RepairsEntity>> callback) {
        Call<List<RepairsEntity>> call = apiRepairs.getRepairs();
        BaseController<List<RepairsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getRepairsById(int id, Callback<RepairsEntity> callback) {
        Call<RepairsEntity> call = apiRepairs.getRepairsById(id);
        BaseController<RepairsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getRepairsByRideId(int id, Callback<List<RepairsEntity>> callback) {
        Call<List<RepairsEntity>> call = apiRepairs.getRepairsByRideId(id);
        BaseController<List<RepairsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deleteRepairById(int id, Callback<Void> callback) {
        Call<Void> call = apiRepairs.deleteRepairsById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createRepair(RepairsEntity repair, Callback<RepairsEntity> callback) {
        Call<RepairsEntity> call = apiRepairs.createRepairs(repair);
        BaseController<RepairsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updateRepairById(int id, RepairsEntity position, final Callback<RepairsEntity> callback) {
        Call<RepairsEntity> call = apiRepairs.updateRepairsById(id, position);
        BaseController<RepairsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}