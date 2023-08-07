package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRides;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class RidesControllerTests {

    private RidesController ridesController;

    @Mock
    private IApiRides mockApiRides;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ridesController = new RidesController();
        ridesController.setApiRides(mockApiRides);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRides() throws IOException {
        List<RidesEntity> expectedRides = new ArrayList<>();
        Call<List<RidesEntity>> mockCall = mock(Call.class);
        Response<List<RidesEntity>> response = Response.success(expectedRides);

        when(mockApiRides.getRides()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RidesEntity>> callback = mock(Callback.class);

        ridesController.getRides(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RidesEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRidesById() throws IOException {
        int rideId = 1;
        RidesEntity expectedRide = new RidesEntity();
        Call<RidesEntity> mockCall = mock(Call.class);
        Response<RidesEntity> response = Response.success(expectedRide);

        when(mockApiRides.getRideById(rideId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<RidesEntity> callback = mock(Callback.class);

        ridesController.getRidesById(rideId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<RidesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeleteRideById() throws IOException {
        int rideId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiRides.deleteRideById(rideId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        ridesController.deleteRideById(rideId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreateRide() throws IOException {
        RidesEntity rideToCreate = new RidesEntity();
        Call<RidesEntity> mockCall = mock(Call.class);
        Response<RidesEntity> response = Response.success(rideToCreate);

        when(mockApiRides.createRide(rideToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<RidesEntity> callback = mock(Callback.class);

        ridesController.createRide(rideToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<RidesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testUpdateRideById() throws IOException {
        long rideId = 1;
        RidesEntity rideToUpdate = new RidesEntity();
        RidesEntity updatedRide = new RidesEntity();
        Call<RidesEntity> mockCall = mock(Call.class);
        Response<RidesEntity> response = Response.success(updatedRide);

        when(mockApiRides.updateRideById(rideId, rideToUpdate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<RidesEntity> callback = mock(Callback.class);

        ridesController.updateRideById(rideId, rideToUpdate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<RidesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

