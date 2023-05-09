package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;

public class UserSession {
    private static UserSession instance;
    private EmployeesEntity employee;

    private UserSession() {
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void login(EmployeesEntity employee) {
        this.employee = employee;
    }

    public void logout() {
        this.employee = null;
    }

    public EmployeesEntity getEmployee() {
        return employee;
    }

    public boolean isLoggedIn() {
        return employee != null;
    }
}
