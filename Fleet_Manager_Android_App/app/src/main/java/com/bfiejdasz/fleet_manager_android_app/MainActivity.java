package com.bfiejdasz.fleet_manager_android_app;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.LoginPanel;
import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private LocationProviderProxy locationProvider;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationProvider = new LocationProviderProxy(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Location currentLocation = locationProvider.getCurrentLocation();
                if (currentLocation != null) {
                    double latitude = currentLocation.getLatitude();
                    double longitude = currentLocation.getLongitude();
                    Log.i("GPS", "Current location: (" + latitude + ", " + longitude + ")");
                } else {
                    Log.i("GPS", "Could not get current location");
                }

                handler.postDelayed(this, 1000); // Aktualizacja co 1 sekundę
            }
        }, 0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EmployeesController employeesController = new EmployeesController();
        employeesController.getUserById(1, new Callback<EmployeesEntity>() {
            @Override
            public void onResponse(Call<EmployeesEntity> call, Response<EmployeesEntity> response) {
                if (response.isSuccessful()) {
                    EmployeesEntity employee = response.body();
                    Log.d("TEST", employee.getName());
                    Log.d("TEST", employee.getSurname());
                    Log.d("TEST", employee.getRole().toString());
                    Log.d("TEST", String.valueOf(employee.getIdEmployees()));
                } else {
                    Log.d("TEST", "BLAD1");
                }
            }

            @Override
            public void onFailure(Call<EmployeesEntity> call, Throwable t) {
                Log.d("TEST", "BLAD2");
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EmployeesController employeesController = new EmployeesController();
        RidesController ridesController = new RidesController();

        RidesEntity ride = new RidesEntity(100,40, 1);
        ridesController.createRide(ride, new Callback<RidesEntity>() {
            @Override
            public void onResponse(Call<RidesEntity> call, Response<RidesEntity> response) {
                Log.d("TEST", "SUKCES");
            }

            @Override
            public void onFailure(Call<RidesEntity> call, Throwable t) {
                Log.d("TEST", "BLAD2 " + t.toString());
            }
        });
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginPanel loginOperation = new LoginPanel();
        Intent intent = new Intent(this, LoginPanel.class);
        startActivity(intent);
        finish();
    }
}

