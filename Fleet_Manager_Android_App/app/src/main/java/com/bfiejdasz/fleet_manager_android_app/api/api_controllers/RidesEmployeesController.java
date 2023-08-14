package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRidesEmployees;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEmployeesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RidesEmployeesController {
    private IApiRidesEmployees apiRidesEmployees;

    public void setApiRidesEmployees(IApiRidesEmployees apiRidesEmployees) {
        this.apiRidesEmployees = apiRidesEmployees;
    }

    public RidesEmployeesController() {
        apiRidesEmployees = ApiClient.getClient().create(IApiRidesEmployees.class);
    }

    public void getRidesEmployees(Callback<List<RidesEmployeesEntity>> callback) {
        Call<List<RidesEmployeesEntity>> call = apiRidesEmployees.getRidesEmployees();
        BaseController<List<RidesEmployeesEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getRidesEmployeesByEmployee(int id, Callback<List<RidesEmployeesEntity>>  callback) {
        Call<List<RidesEmployeesEntity>> call = apiRidesEmployees.getRidesEmployeesByEmployee(id);
        BaseController<List<RidesEmployeesEntity>>  baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getRidesEmployeesByRide(int id, Callback<List<RidesEmployeesEntity>>  callback) {
        Call<List<RidesEmployeesEntity>> call = apiRidesEmployees.getRidesEmployeesByRide(id);
        BaseController<List<RidesEmployeesEntity>>  baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void addRideEmployee(List<RidesEmployeesEntity> ridesEmployeesEntities, Callback<List<RidesEmployeesEntity>> callback) {
        Call<List<RidesEmployeesEntity>> call = apiRidesEmployees.addRideEmployee(ridesEmployeesEntities);
        BaseController<List<RidesEmployeesEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
