package com.bfiejdasz.fleet_manager_android_app.api.api_interfaces;

import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IApiVehicles {
    @GET("vehicles/getAll")
    Call<List<VehiclesEntity>> getVehicles();

    @GET("vehicles")
    Call<VehiclesEntity> getVehiclesById(@Query("id") int id);

    @DELETE("vehicles")
    Call<Void> deleteVehiclesById(@Query("id") int id);

    @POST("vehicles")
    Call<VehiclesEntity> createVehicles(@Body VehiclesEntity vehicle);

    @PATCH("vehicles")
    Call<VehiclesEntity> updateVehiclesById(@Query("id") int id, @Body VehiclesEntity vehicle);

    @GET("vehicles/getByPlate")
    Call<VehiclesEntity> getVehicleByLicensePlate(@Query("licensePlate") String licensePlate);

    @GET("vehicles/inUse")
    Call<Short> checkInUse(@Query("licencePlate") String licencePlate);

    @PUT("vehicles/changeInUseVehicle")
    Call<Void> changeInUseVehicle(@Query("licencePlate") String licencePlate, @Query("inUse") Short inUse);
}
