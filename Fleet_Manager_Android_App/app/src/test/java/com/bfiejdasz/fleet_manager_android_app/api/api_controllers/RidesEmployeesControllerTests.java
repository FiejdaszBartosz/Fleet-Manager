package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesEmployeesController;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRidesEmployees;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEmployeesEntity;

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

class RidesEmployeesControllerTests {

    private RidesEmployeesController ridesEmployeesController;

    @Mock
    private IApiRidesEmployees mockApiRidesEmployees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ridesEmployeesController = new RidesEmployeesController();
        ridesEmployeesController.setApiRidesEmployees(mockApiRidesEmployees);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRidesEmployees() throws IOException {
        List<RidesEmployeesEntity> expectedRidesEmployees = new ArrayList<>();
        Call<List<RidesEmployeesEntity>> mockCall = mock(Call.class);
        Response<List<RidesEmployeesEntity>> response = Response.success(expectedRidesEmployees);

        when(mockApiRidesEmployees.getRidesEmployees()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RidesEmployeesEntity>> callback = mock(Callback.class);

        ridesEmployeesController.getRidesEmployees(callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RidesEmployeesEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRidesEmployeesByEmployee() throws IOException {
        int employeeId = 1;
        List<RidesEmployeesEntity> expectedRidesEmployees = new ArrayList<>();
        Call<List<RidesEmployeesEntity>> mockCall = mock(Call.class);
        Response<List<RidesEmployeesEntity>> response = Response.success(expectedRidesEmployees);

        when(mockApiRidesEmployees.getRidesEmployeesByEmployee(employeeId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RidesEmployeesEntity>> callback = mock(Callback.class);

        ridesEmployeesController.getRidesEmployeesByEmployee(employeeId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RidesEmployeesEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRidesEmployeesByRide() throws IOException {
        int rideId = 1;
        List<RidesEmployeesEntity> expectedRidesEmployees = new ArrayList<>();
        Call<List<RidesEmployeesEntity>> mockCall = mock(Call.class);
        Response<List<RidesEmployeesEntity>> response = Response.success(expectedRidesEmployees);

        when(mockApiRidesEmployees.getRidesEmployeesByRide(rideId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RidesEmployeesEntity>> callback = mock(Callback.class);

        ridesEmployeesController.getRidesEmployeesByRide(rideId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RidesEmployeesEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAddRideEmployee() throws IOException {
        List<RidesEmployeesEntity> ridesEmployeesToAdd = new ArrayList<>();
        Call<List<RidesEmployeesEntity>> mockCall = mock(Call.class);
        Response<List<RidesEmployeesEntity>> response = Response.success(ridesEmployeesToAdd);

        when(mockApiRidesEmployees.addRideEmployee(ridesEmployeesToAdd)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<List<RidesEmployeesEntity>> callback = mock(Callback.class);

        ridesEmployeesController.addRideEmployee(ridesEmployeesToAdd, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<List<RidesEmployeesEntity>>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}
