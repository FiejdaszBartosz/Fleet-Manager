package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.VehiclesController;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiVehicles;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;

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

class VehiclesControllerTests {

    private VehiclesController vehiclesController;

    @Mock
    private IApiVehicles mockApiVehicles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehiclesController = new VehiclesController();
        vehiclesController.setApiVehicles(mockApiVehicles);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetVehicles() throws IOException {
        List<VehiclesEntity> expectedVehicles = new ArrayList<>();
        Call<List<VehiclesEntity>> mockCall = mock(Call.class);
        Response<List<VehiclesEntity>> response = Response.success(expectedVehicles);

        when(mockApiVehicles.getVehicles()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<VehiclesEntity>> callback = mock(Callback.class);

        vehiclesController.getVehicles(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<VehiclesEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetVehiclesById() throws IOException {
        int vehicleId = 1;
        VehiclesEntity expectedVehicle = new VehiclesEntity();
        Call<VehiclesEntity> mockCall = mock(Call.class);
        Response<VehiclesEntity> response = Response.success(expectedVehicle);

        when(mockApiVehicles.getVehiclesById(vehicleId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<VehiclesEntity> callback = mock(Callback.class);

        vehiclesController.getVehiclesById(vehicleId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<VehiclesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeleteVehiclesById() throws IOException {
        int vehicleId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiVehicles.deleteVehiclesById(vehicleId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        vehiclesController.deleteVehiclesById(vehicleId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreateUser() throws IOException {
        VehiclesEntity vehicleToCreate = new VehiclesEntity();
        VehiclesEntity createdVehicle = new VehiclesEntity();
        Call<VehiclesEntity> mockCall = mock(Call.class);
        Response<VehiclesEntity> response = Response.success(createdVehicle);

        when(mockApiVehicles.createVehicles(vehicleToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<VehiclesEntity> callback = mock(Callback.class);

        vehiclesController.createUser(vehicleToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<VehiclesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testUpdateVehiclesById() throws IOException {
        int vehicleId = 1;
        VehiclesEntity vehicleToUpdate = new VehiclesEntity();
        VehiclesEntity updatedVehicle = new VehiclesEntity();
        Call<VehiclesEntity> mockCall = mock(Call.class);
        Response<VehiclesEntity> response = Response.success(updatedVehicle);

        when(mockApiVehicles.updateVehiclesById(vehicleId, vehicleToUpdate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<VehiclesEntity> callback = mock(Callback.class);

        vehiclesController.updateVehiclesById(vehicleId, vehicleToUpdate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<VehiclesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetVehiclesByLicencePlate() throws IOException {
        String licencePlate = "ABC123";
        VehiclesEntity expectedVehicle = new VehiclesEntity();
        Call<VehiclesEntity> mockCall = mock(Call.class);
        Response<VehiclesEntity> response = Response.success(expectedVehicle);

        when(mockApiVehicles.getVehicleByLicensePlate(licencePlate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<VehiclesEntity> callback = mock(Callback.class);

        vehiclesController.getVehiclesByLicencePlate(licencePlate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<VehiclesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testIsVehicleInUse() throws IOException {
        String licencePlate = "ABC123";
        short inUse = 1;
        Call<Short> mockCall = mock(Call.class);
        Response<Short> response = Response.success(inUse);

        when(mockApiVehicles.checkInUse(licencePlate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Short> callback = mock(Callback.class);

        vehiclesController.isVehicleInUse(licencePlate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Short>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testBookVehicle() throws IOException {
        String licencePlate = "ABC123";
        short inUse = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiVehicles.changeInUseVehicle(licencePlate, inUse)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        vehiclesController.bookVehicle(licencePlate, inUse, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

