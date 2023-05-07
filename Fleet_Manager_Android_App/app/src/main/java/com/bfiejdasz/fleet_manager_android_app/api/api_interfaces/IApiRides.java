package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiRides {
    @GET("rides/getAll")
    Call<List<RidesEntity>> getRides();

    @GET("rides")
    Call<RidesEntity> getRideById(@Query("id") int id);

    @DELETE("rides")
    Call<Void> deleteRideById(@Query("id") int id);

    @POST("rides")
    Call<RidesEntity> createRide(@Body RidesEntity ride);

    @PATCH("rides")
    Call<RidesEntity> updateRideById(@Query("id") int id, @Body RidesEntity ride);

}
