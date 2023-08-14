package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiProblems;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;

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

class ProblemsControllerTests {

    private ProblemsController problemsController;

    @Mock
    private IApiProblems mockApiProblems;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        problemsController = new ProblemsController();
        problemsController.setApiProblems(mockApiProblems);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetProblems() throws IOException {
        List<ProblemsEntity> expectedProblems = new ArrayList<>();
        Call<List<ProblemsEntity>> mockCall = mock(Call.class);
        Response<List<ProblemsEntity>> response = Response.success(expectedProblems);

        when(mockApiProblems.getProblems()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<ProblemsEntity>> callback = mock(Callback.class);

        problemsController.getProblems(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<ProblemsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetProblemById() throws IOException {
        int problemId = 1;
        ProblemsEntity expectedProblem = new ProblemsEntity();
        Call<ProblemsEntity> mockCall = mock(Call.class);
        Response<ProblemsEntity> response = Response.success(expectedProblem);

        when(mockApiProblems.getProblemsById(problemId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<ProblemsEntity> callback = mock(Callback.class);

        problemsController.getProblemById(problemId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<ProblemsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetProblemsByRideId() throws IOException {
        int rideId = 1;
        List<ProblemsEntity> expectedProblems = new ArrayList<>();
        Call<List<ProblemsEntity>> mockCall = mock(Call.class);
        Response<List<ProblemsEntity>> response = Response.success(expectedProblems);

        when(mockApiProblems.getProblemsByRideId(rideId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<ProblemsEntity>> callback = mock(Callback.class);

        problemsController.getProblemsByRideId(rideId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<ProblemsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeleteProblemById() throws IOException {
        int problemId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiProblems.deleteProblemById(problemId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        problemsController.deleteProblemById(problemId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreateProblem() throws IOException {
        List<ProblemsEntity> problemToCreate = new ArrayList<>();
        ProblemsEntity problem = new ProblemsEntity();
        problemToCreate.add(problem);
        Call<List<ProblemsEntity>> mockCall = mock(Call.class);
        Response<List<ProblemsEntity>> response = Response.success(problemToCreate);

        when(mockApiProblems.createProblem(problemToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<ProblemsEntity>> callback = mock(Callback.class);

        problemsController.createProblem(problemToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<ProblemsEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testUpdateProblemById() throws IOException {
        int problemId = 1;
        ProblemsEntity problemToUpdate = new ProblemsEntity();
        ProblemsEntity updatedProblem = new ProblemsEntity();
        Call<ProblemsEntity> mockCall = mock(Call.class);
        Response<ProblemsEntity> response = Response.success(updatedProblem);

        when(mockApiProblems.updateProblemById(problemId, problemToUpdate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<ProblemsEntity> callback = mock(Callback.class);

        problemsController.updateProblemById(problemId, problemToUpdate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<ProblemsEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

