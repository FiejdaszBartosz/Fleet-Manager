package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiEmployees;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class EmployeesControllerTests {

    @InjectMocks
    private EmployeesController employeesController;

    @Mock
    private IApiEmployees mockApiEmployees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeesController = new EmployeesController();
        employeesController.setApiEmployees(mockApiEmployees);
    }


    @SuppressWarnings("unchecked")
    @Test
    void testGetUserById() throws IOException {
        int userId = 1;
        EmployeesEntity expectedUser = new EmployeesEntity();
        Call<EmployeesEntity> mockCall = mock(Call.class);
        Response<EmployeesEntity> response = Response.success(expectedUser);

        when(mockApiEmployees.getUserById(userId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<EmployeesEntity> callback = mock(Callback.class);

        employeesController.getUserById(userId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<EmployeesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testDeleteUserById() throws IOException {
        int userId = 1;
        Call<Void> mockCall = mock(Call.class);
        Response<Void> response = Response.success(null);

        when(mockApiEmployees.deleteUserById(userId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<Void> callback = mock(Callback.class);

        employeesController.deleteUserById(userId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<Void>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCreateUser() throws IOException {
        EmployeesEntity userToCreate = new EmployeesEntity();
        EmployeesEntity createdUser = new EmployeesEntity();
        Call<EmployeesEntity> mockCall = mock(Call.class);
        Response<EmployeesEntity> response = Response.success(createdUser);

        when(mockApiEmployees.createUser(userToCreate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<EmployeesEntity> callback = mock(Callback.class);

        employeesController.createUser(userToCreate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<EmployeesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }


    @SuppressWarnings("unchecked")
    @Test
    void testUpdateUserById() throws IOException {
        int userId = 1;
        EmployeesEntity userToUpdate = new EmployeesEntity();
        EmployeesEntity updatedUser = new EmployeesEntity();
        Call<EmployeesEntity> mockCall = mock(Call.class);
        Response<EmployeesEntity> response = Response.success(updatedUser);

        when(mockApiEmployees.updateUserById(userId, userToUpdate)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<EmployeesEntity> callback = mock(Callback.class);

        employeesController.updateUserById(userId, userToUpdate, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<EmployeesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testCheckCredentials() throws IOException {
        String login = "test_login";
        String pass = "test_password";
        EmployeesEntity userWithCredentials = new EmployeesEntity();
        Call<EmployeesEntity> mockCall = mock(Call.class);
        Response<EmployeesEntity> response = Response.success(userWithCredentials);

        when(mockApiEmployees.checkCredentials(login, pass)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<EmployeesEntity> callback = mock(Callback.class);

        employeesController.checkCredentials(login, pass, callback);

        verify(mockApiEmployees).checkCredentials(login, pass);
        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<EmployeesEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

