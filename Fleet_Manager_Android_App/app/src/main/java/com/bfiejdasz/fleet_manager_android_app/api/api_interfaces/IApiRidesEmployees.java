package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEmployeesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiRidesEmployees {
    @GET("ridesEmployees/getAll")
    Call<List<RidesEmployeesEntity>> getRidesEmployees();

    @GET("ridesEmployees/getByEmployee")
    Call<List<RidesEmployeesEntity>> getRidesEmployeesByEmployee(@Query("id") int id);

    @GET("ridesEmployees/getByEmployee")
    Call<List<RidesEmployeesEntity>> getRidesEmployeesByRide(@Query("id") int id);

    @POST("ridesEmployees")
    Call<List<RidesEmployeesEntity>> addRideEmployee(@Body List<RidesEmployeesEntity> ridesEmployees);
}
