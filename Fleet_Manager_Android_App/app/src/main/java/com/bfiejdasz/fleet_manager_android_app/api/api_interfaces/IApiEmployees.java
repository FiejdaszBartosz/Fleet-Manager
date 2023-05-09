package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiEmployees {
    @GET("employees/getAll")
    Call<List<EmployeesEntity>> getUsers();

    @GET("employees")
    Call<EmployeesEntity> getUserById(@Query("id") int id);

    @DELETE("employees")
    Call<Void> deleteUserById(@Query("id") int id);

    @POST("employees")
    Call<EmployeesEntity> createUser(@Body EmployeesEntity employee);

    @PATCH("employees")
    Call<EmployeesEntity> updateUserById(@Query("id") int id, @Body EmployeesEntity employee);

    @GET("employees/login")
    Call<EmployeesEntity> checkCredentials(@Query("login") String login, @Query("password") String pass);
}
