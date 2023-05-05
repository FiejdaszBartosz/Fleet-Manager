package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiEmployees;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class EmployeesController {
    private final IApiEmployees apiEmployees;

    public EmployeesController() {
        apiEmployees = ApiClient.getClient().create(IApiEmployees.class);
    }

    public void getUsers(Callback<List<EmployeesEntity>> callback) {
        Call<List<EmployeesEntity>> call = apiEmployees.getUsers();
        BaseController<List<EmployeesEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getUserById(int id, Callback<EmployeesEntity> callback) {
        Call<EmployeesEntity> call = apiEmployees.getUserById(id);
        BaseController<EmployeesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deleteUserById(int id, Callback<Void> callback) {
        Call<Void> call = apiEmployees.deleteUserById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createUser(EmployeesEntity employee, Callback<EmployeesEntity> callback) {
        Call<EmployeesEntity> call = apiEmployees.createUser(employee);
        BaseController<EmployeesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updateUserById(int id, EmployeesEntity employee, final Callback<EmployeesEntity> callback) {
        Call<EmployeesEntity> call = apiEmployees.updateUserById(id, employee);
        BaseController<EmployeesEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

}
