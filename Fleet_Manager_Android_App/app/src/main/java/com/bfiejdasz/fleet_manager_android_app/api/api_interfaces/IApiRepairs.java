package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.RepairsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiRepairs {
    @GET("repairs/getAll")
    Call<List<RepairsEntity>> getRepairs();

    @GET("repairs")
    Call<RepairsEntity> getRepairsById(@Query("id") int id);

    @GET("repairs/rideid")
    Call<List<RepairsEntity>> getRepairsByRideId(@Query("id") int id);

    @DELETE("repairs")
    Call<Void> deleteRepairsById(@Query("id") int id);

    @POST("repairs")
    Call<List<RepairsEntity>> createRepairs(@Body List<RepairsEntity> repairs);

    @PATCH("repairs")
    Call<RepairsEntity> updateRepairsById(@Query("id") int id, @Body RepairsEntity repair);

}
