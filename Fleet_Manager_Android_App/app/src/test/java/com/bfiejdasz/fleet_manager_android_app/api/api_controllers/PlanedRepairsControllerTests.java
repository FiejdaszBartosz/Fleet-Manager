package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiPlanedRepairs;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PlanedRepairsEntity;

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

class PlanedRepairsControllerTests {

    private PlanedRepairsController planedRepairsController;

    @Mock
    private IApiPlanedRepairs mockApiPlanedRepairs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        planedRepairsController = new PlanedRepairsController();
        planedRepairsController.setApiPlanedRepairs(mockApiPlanedRepairs);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetPlanedRepairs() throws IOException {
        List<PlanedRepairsEntity> expectedPlanedRepairs = new ArrayList<>();
        Call<List<PlanedRepairsEntity>> mockCall = mock(Call.class);
        Response<List<PlanedRepairsEntity>> response = Response.success(expectedPlanedRepairs);

        when(mockApiPlanedRepairs.getUsers()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<PlanedRepairsEntity>> callback = mock(Callback.class);

        planedRepairsController.getPlanedRepairs(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<PlanedRepairsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetPlanedRepairsByVehicleId() throws IOException {
        long vehicleId = 1L;
        List<PlanedRepairsEntity> expectedPlanedRepairs = new ArrayList<>();
        Call<List<PlanedRepairsEntity>> mockCall = mock(Call.class);
        Response<List<PlanedRepairsEntity>> response = Response.success(expectedPlanedRepairs);

        when(mockApiPlanedRepairs.getUserByVehicleId(vehicleId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<PlanedRepairsEntity>> callback = mock(Callback.class);

        planedRepairsController.getPlanedRepairsByVehicleId(vehicleId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<PlanedRepairsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeletePlanedRepairsById() throws IOException {
        int planedRepairsId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiPlanedRepairs.deletePlanedRepairsById(planedRepairsId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        planedRepairsController.deletePlanedRepairsById(planedRepairsId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreatePlanedRepairs() throws IOException {
        PlanedRepairsEntity planedRepairsToCreate = new PlanedRepairsEntity();
        PlanedRepairsEntity createdPlanedRepairs = new PlanedRepairsEntity();
        Call<PlanedRepairsEntity> mockCall = mock(Call.class);
        Response<PlanedRepairsEntity> response = Response.success(createdPlanedRepairs);

        when(mockApiPlanedRepairs.createPlanedRepairs(planedRepairsToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<PlanedRepairsEntity> callback = mock(Callback.class);

        planedRepairsController.createPlanedRepairs(planedRepairsToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<PlanedRepairsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

