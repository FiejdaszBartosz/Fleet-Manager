package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiPositions {
    @GET("positions/getAll")
    Call<List<PositionsEntity>> getPositions();

    @GET("positions")
    Call<PositionsEntity> getPositionById(@Query("id") int id);

    @GET("positions/rideid")
    Call<List<PositionsEntity>> getPositionsByRideId(@Query("id") int id);

    @DELETE("positions")
    Call<Void> deletePositionById(@Query("id") int id);

    @POST("positions")
    Call<List<PositionsEntity>> createPosition(@Body List<PositionsEntity> position);

    @PATCH("positions")
    Call<PositionsEntity> updatePositionById(@Query("id") int id, @Body PositionsEntity position);
}
