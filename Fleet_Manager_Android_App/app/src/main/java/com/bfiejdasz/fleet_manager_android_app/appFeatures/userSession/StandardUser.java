package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;

public class StandardUser extends AppCompatActivity implements IUser {
    private EmployeesEntity employee;
    private Context context;

    public StandardUser() {
        this.context = this;
        this.employee = UserSession.getInstance().getEmployee();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standar_user_main_panel);

        TextView userNameTextView = findViewById(R.id.userNameTextView);
        userNameTextView.setText(employee.getName());
    }

    @Override
    public EmployeesEntity getUser() {
        return this.employee;
    }
}

