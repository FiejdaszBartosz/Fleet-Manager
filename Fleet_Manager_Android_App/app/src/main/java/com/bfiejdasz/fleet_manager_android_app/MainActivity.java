package com.bfiejdasz.fleet_manager_android_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.PermissionsActivity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.LoginPanel;

import org.osmdroid.config.Configuration;

import java.io.File;

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

