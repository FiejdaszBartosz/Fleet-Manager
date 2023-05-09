package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.CustomHttpException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class BaseController<T> {

    private final Call<T> call;

    public BaseController(Call<T> call) {
        this.call = call;
    }

    public void execute(Callback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    int statusCode = response.code();
                    String errorMessage = response.message();
                    CustomHttpException customHttpException = new CustomHttpException(errorMessage, statusCode);
                    callback.onFailure(call, customHttpException);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}

