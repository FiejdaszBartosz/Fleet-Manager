package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.DriverFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;

public class StandardUser extends AppCompatActivity implements IUser {
    private EmployeesEntity employee;
    private ApplicationContextSingleton appContext;
    private Button startButton;
    private TextView userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standar_user_main_panel);

        userNameTextView = findViewById(R.id.userNameTextView);
        startButton = findViewById(R.id.startButton);

        this.employee = UserSession.getInstance().getEmployee();

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        userNameTextView.setText(employee.getName());

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRidePanel();
            }
        });
    }

    @Override
    public EmployeesEntity getUser() {
        return this.employee;
    }

    private void startRidePanel() {
        RideFactorySingleton temp = RideFactorySingleton.getInstance();
        temp.setRideFactory(new DriverFactory());
        RideSession rideSession = RideSession.getInstance();
        rideSession.setRide();
        Intent intent = new Intent(this, ChooseVehiclePanel.class);
        startActivity(intent);
        finish();
    }
}

