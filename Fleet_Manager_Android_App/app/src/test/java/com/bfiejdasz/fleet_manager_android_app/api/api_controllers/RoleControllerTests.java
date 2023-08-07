package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RoleController;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiRole;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RoleEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

import static org.mockito.Mockito.*;

class RoleControllerTests {

    private RoleController roleController;

    @Mock
    private IApiRole mockApiRole;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleController = new RoleController();
        roleController.setApiRole(mockApiRole);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetRoleById() throws IOException {
        int roleId = 1;
        RoleEntity expectedRole = new RoleEntity();
        Call<RoleEntity> mockCall = mock(Call.class);
        Response<RoleEntity> response = Response.success(expectedRole);

        when(mockApiRole.getRoleById(roleId)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(response);

        Callback<RoleEntity> callback = mock(Callback.class);

        roleController.getRoleById(roleId, callback);

        verify(mockCall).enqueue(any());

        ArgumentCaptor<Callback<RoleEntity>> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(mockCall, response);
    }
}

