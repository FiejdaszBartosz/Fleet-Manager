package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.IUser;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.UserSession;

public class ManagerRepairPanel extends AppCompatActivity implements IUser {
    private TextView welcomeTextView;
    private Button addRepairButton;
    private Button repairStatusButton;
    private Button vehicleStatusButton;
    private EmployeesEntity employee;
    private ApplicationContextSingleton appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_repairs_panel);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        addRepairButton = findViewById(R.id.addRepairButtonToPanel);
        repairStatusButton = findViewById(R.id.repairStatusButton);
        vehicleStatusButton = findViewById(R.id.vehicleStatusButton);

        welcomeTextView.setText(getString(R.string.repair_panel_title));
        addRepairButton.setText(getString(R.string.add_repair_button));
        repairStatusButton.setText(getString(R.string.change_repair_status_button));
        vehicleStatusButton.setText(getString(R.string.vehicle_status_button));

        this.employee = UserSession.getInstance().getEmployee();

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        addRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddRepairPreview();
            }
        });

        repairStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairStatus();
            }
        });

        vehicleStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVehicleStatusPanel();
            }
        });
    }

    private void openAddRepairPreview() {
        Toast.makeText(appContext.getAppContext(), "Otwórz podgląd przejazdu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddRepairPanel.class);
        startActivity(intent);
        finish();
    }

    private void openRepairStatus() {
        Intent intent = new Intent(this, RepairStatusPanel.class);
        startActivity(intent);
        finish();
    }

    private void openVehicleStatusPanel() {
        Intent intent = new Intent(this, VehicleStatusPanel.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public EmployeesEntity getUser() {
        return this.employee;
    }
}
