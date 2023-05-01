package com.bfiejdasz.fleet_manager_android_app;

import android.location.Location;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

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
}

