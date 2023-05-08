package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRole;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RoleEntity;

import retrofit2.Call;
import retrofit2.Callback;

public class RoleController {
    private final IApiRole apiRole;

    public RoleController() {
        apiRole = ApiClient.getClient().create(IApiRole.class);
    }

    public void getRoleById(int id, Callback<RoleEntity> callback) {
        Call<RoleEntity> call = apiRole.getRoleById(id);
        BaseController<RoleEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
