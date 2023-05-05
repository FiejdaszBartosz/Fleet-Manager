package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.PlanedRepairsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiPlanedRepairs {
    @GET("planedRepairs/getAll")
    Call<List<PlanedRepairsEntity>> getUsers();

    @GET("planedRepairs")
    Call<PlanedRepairsEntity> getUserByVehicleId(@Query("id") int id);

    @DELETE("planedRepairs")
    Call<Void> deletePlanedRepairsById(@Query("id") int id);

    @POST("planedRepairs")
    Call<PlanedRepairsEntity> createPlanedRepairs(@Body PlanedRepairsEntity planedRepairs);

    @PATCH("planedRepairs")
    Call<PlanedRepairsEntity> updatePlanedRepairsById(@Query("id") int id, @Body PlanedRepairsEntity planedRepairs);
}
