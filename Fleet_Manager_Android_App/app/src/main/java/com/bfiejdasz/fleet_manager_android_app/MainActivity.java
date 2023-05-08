package com.bfiejdasz.fleet_manager_android_app;

import android.location.Location;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.EmployeesController;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LocationProviderProxy locationProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationProvider = new LocationProviderProxy(this);

        Location currentLocation = locationProvider.getCurrentLocation();
        if (currentLocation != null) {
            double latitude = currentLocation.getLatitude();
            double longitude = currentLocation.getLongitude();
            Log.i("MainActivity", "Current location: (" + latitude + ", " + longitude + ")");
        } else {
            Log.i("MainActivity", "Could not get current location");
        }

    }

    /*
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
    }
     */
}

