package com.bfiejdasz.fleet_manager_android_app;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.LoginPanel;
import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationContextSingleton applicationContextSingleton = ApplicationContextSingleton.getInstance();

        LoginPanel loginOperation = new LoginPanel();
        Intent intent = new Intent(this, LoginPanel.class);
        startActivity(intent);
        finish();
    }
}

