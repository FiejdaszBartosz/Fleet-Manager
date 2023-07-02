package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiProblems {
    @GET("problems/getAll")
    Call<List<ProblemsEntity>> getProblems();

    @GET("problems")
    Call<ProblemsEntity> getProblemsById(@Query("id") int id);

    @GET("problems/rideid")
    Call<List<ProblemsEntity>> getProblemsByRideId(@Query("id") int id);

    @DELETE("problems")
    Call<Void> deleteProblemById(@Query("id") int id);

    @POST("problems")
    Call<List<ProblemsEntity>> createProblem(@Body List<ProblemsEntity>  position);

    @PATCH("problems")
    Call<ProblemsEntity> updateProblemById(@Query("id") int id, @Body ProblemsEntity position);
}
