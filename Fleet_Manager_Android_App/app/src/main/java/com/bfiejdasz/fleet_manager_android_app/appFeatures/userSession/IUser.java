package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;

public interface IUser {
    EmployeesEntity getUser();
}
