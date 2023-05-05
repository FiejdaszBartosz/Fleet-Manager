package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiPlanedRepairs;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PlanedRepairsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PlanedRepairsController {
    private final IApiPlanedRepairs apiPlanedRepairs;

    public PlanedRepairsController() {
        apiPlanedRepairs = ApiClient.getClient().create(IApiPlanedRepairs.class);
    }

    public void getPlanedRepairs(Callback<List<PlanedRepairsEntity>> callback) {
        Call<List<PlanedRepairsEntity>> call = apiPlanedRepairs.getUsers();
        BaseController<List<PlanedRepairsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getPlanedRepairsByVehicleId(int id, Callback<PlanedRepairsEntity> callback) {
        Call<PlanedRepairsEntity> call = apiPlanedRepairs.getUserByVehicleId(id);
        BaseController<PlanedRepairsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deletePlanedRepairsById(int id, Callback<Void> callback) {
        Call<Void> call = apiPlanedRepairs.deletePlanedRepairsById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createPlanedRepairs(PlanedRepairsEntity planedRepairs, Callback<PlanedRepairsEntity> callback) {
        Call<PlanedRepairsEntity> call = apiPlanedRepairs.createPlanedRepairs(planedRepairs);
        BaseController<PlanedRepairsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updateUserById(int id, PlanedRepairsEntity planedRepairs, final Callback<PlanedRepairsEntity> callback) {
        Call<PlanedRepairsEntity> call = apiPlanedRepairs.updatePlanedRepairsById(id, planedRepairs);
        BaseController<PlanedRepairsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
