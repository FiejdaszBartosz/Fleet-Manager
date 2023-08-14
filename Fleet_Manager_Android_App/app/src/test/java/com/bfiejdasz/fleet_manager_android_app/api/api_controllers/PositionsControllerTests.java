package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiPositions;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PositionsControllerTests {

    private PositionsController positionsController;

    @Mock
    private IApiPositions mockApiPositions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        positionsController = new PositionsController();
        positionsController.setApiPositions(mockApiPositions);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetPositions() throws IOException {
        List<PositionsEntity> expectedPositions = new ArrayList<>();
        Call<List<PositionsEntity>> mockCall = mock(Call.class);
        Response<List<PositionsEntity>> response = Response.success(expectedPositions);

        when(mockApiPositions.getPositions()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<PositionsEntity>> callback = mock(Callback.class);

        positionsController.getPositions(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<PositionsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetPositionsById() throws IOException {
        int positionId = 1;
        PositionsEntity expectedPosition = new PositionsEntity();
        Call<PositionsEntity> mockCall = mock(Call.class);
        Response<PositionsEntity> response = Response.success(expectedPosition);

        when(mockApiPositions.getPositionById(positionId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<PositionsEntity> callback = mock(Callback.class);

        positionsController.getPositionsById(positionId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<PositionsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetPositionsByRideId() throws IOException {
        int rideId = 1;
        List<PositionsEntity> expectedPositions = new ArrayList<>();
        Call<List<PositionsEntity>> mockCall = mock(Call.class);
        Response<List<PositionsEntity>> response = Response.success(expectedPositions);

        when(mockApiPositions.getPositionsByRideId(rideId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<PositionsEntity>> callback = mock(Callback.class);

        positionsController.getPositionsByRideId(rideId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<PositionsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetLatestPositionsForAllRides() throws IOException {
        List<PositionsEntity> expectedPositions = new ArrayList<>();
        Call<List<PositionsEntity>> mockCall = mock(Call.class);
        Response<List<PositionsEntity>> response = Response.success(expectedPositions);

        when(mockApiPositions.getLatestPositionsForAllRides()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<PositionsEntity>> callback = mock(Callback.class);

        positionsController.getLatestPositionsForAllRides(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<PositionsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeletePositionsById() throws IOException {
        int positionId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiPositions.deletePositionById(positionId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        positionsController.deletePositionsById(positionId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreatePosition() throws IOException {
        List<PositionsEntity> positionToCreate = new ArrayList<>();
        PositionsEntity position = new PositionsEntity();
        positionToCreate.add(position);
        Call<List<PositionsEntity>> mockCall = mock(Call.class);
        Response<List<PositionsEntity>> response = Response.success(positionToCreate);

        when(mockApiPositions.createPosition(positionToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<PositionsEntity>> callback = mock(Callback.class);

        positionsController.createPosition(positionToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<PositionsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testUpdatePositionsById() throws IOException {
        int positionId = 1;
        PositionsEntity positionToUpdate = new PositionsEntity();
        PositionsEntity updatedPosition = new PositionsEntity();
        Call<PositionsEntity> mockCall = mock(Call.class);
        Response<PositionsEntity> response = Response.success(updatedPosition);

        when(mockApiPositions.updatePositionById(positionId, positionToUpdate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<PositionsEntity> callback = mock(Callback.class);

        positionsController.updatePositionsById(positionId, positionToUpdate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<PositionsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}
