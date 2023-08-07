package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RepairsController;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRepairs;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RepairsEntity;

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

class RepairsControllerTests {

    private RepairsController repairsController;

    @Mock
    private IApiRepairs mockApiRepairs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repairsController = new RepairsController();
        repairsController.setApiRepairs(mockApiRepairs);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRepairs() throws IOException {
        List<RepairsEntity> expectedRepairs = new ArrayList<>();
        Call<List<RepairsEntity>> mockCall = mock(Call.class);
        Response<List<RepairsEntity>> response = Response.success(expectedRepairs);

        when(mockApiRepairs.getRepairs()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RepairsEntity>> callback = mock(Callback.class);

        repairsController.getRepairs(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RepairsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRepairsById() throws IOException {
        int repairId = 1;
        RepairsEntity expectedRepair = new RepairsEntity();
        Call<RepairsEntity> mockCall = mock(Call.class);
        Response<RepairsEntity> response = Response.success(expectedRepair);

        when(mockApiRepairs.getRepairsById(repairId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<RepairsEntity> callback = mock(Callback.class);

        repairsController.getRepairsById(repairId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<RepairsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRepairsByRideId() throws IOException {
        int rideId = 1;
        List<RepairsEntity> expectedRepairs = new ArrayList<>();
        Call<List<RepairsEntity>> mockCall = mock(Call.class);
        Response<List<RepairsEntity>> response = Response.success(expectedRepairs);

        when(mockApiRepairs.getRepairsByRideId(rideId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RepairsEntity>> callback = mock(Callback.class);

        repairsController.getRepairsByRideId(rideId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RepairsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeleteRepairById() throws IOException {
        int repairId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiRepairs.deleteRepairsById(repairId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        repairsController.deleteRepairById(repairId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreateRepair() throws IOException {
        List<RepairsEntity> repairToCreate = new ArrayList<>();
        RepairsEntity repair = new RepairsEntity();
        repairToCreate.add(repair);
        Call<List<RepairsEntity>> mockCall = mock(Call.class);
        Response<List<RepairsEntity>> response = Response.success(repairToCreate);

        when(mockApiRepairs.createRepairs(repairToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RepairsEntity>> callback = mock(Callback.class);

        repairsController.createRepair(repairToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RepairsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testUpdateRepairById() throws IOException {
        int repairId = 1;
        RepairsEntity repairToUpdate = new RepairsEntity();
        RepairsEntity updatedRepair = new RepairsEntity();
        Call<RepairsEntity> mockCall = mock(Call.class);
        Response<RepairsEntity> response = Response.success(updatedRepair);

        when(mockApiRepairs.updateRepairsById(repairId, repairToUpdate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<RepairsEntity> callback = mock(Callback.class);

        repairsController.updateRepairById(repairId, repairToUpdate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<RepairsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

