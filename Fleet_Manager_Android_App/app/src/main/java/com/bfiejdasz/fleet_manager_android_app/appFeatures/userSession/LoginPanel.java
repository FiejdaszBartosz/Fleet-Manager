package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.EmployeesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession.ManagerMainPanel;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPanel extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private Button loginButton;
    private ApplicationContextSingleton appContext;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_panel);

        usernameEditText = findViewById(R.id.usernameEditText);
        usernameTextView = findViewById(R.id.usernameTextView);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordTextView = findViewById(R.id.passwordTextView);
        loginButton = findViewById(R.id.loginButton);

        usernameTextView.setText(getString(R.string.usernameTextView));
        passwordTextView.setText(getString(R.string.passwordTextView));
        loginButton.setText(getString(R.string.loginButton));

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        this.userSession = UserSession.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClick();
            }
        });
    }

    private void onLoginButtonClick() {
        String login = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(appContext.getAppContext(), "Uzupełnij login i hasło", Toast.LENGTH_SHORT).show();
        } else {

            EmployeesController employeesController = new EmployeesController();
            employeesController.checkCredentials(login, password, new Callback<EmployeesEntity>() {
                @Override
                public void onResponse(Call<EmployeesEntity> call, Response<EmployeesEntity> response) {
                    if (response.isSuccessful()) {
                        userSession.login(response.body());
                        choosePanelByRole(userSession.getEmployee());
                    } else {
                        Toast.makeText(appContext.getAppContext(), "Nieoczekiwany błąd", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EmployeesEntity> call, Throwable t) {
                    try {
                        ErrorHandler.handleException(t);
                    } catch (Exception e) {
                        ErrorHandler.logWithToastErrors(appContext.getAppContext(), e);
                    }
                }
            });
        }
    }

    private void startStandardUserActivity() {
        Intent intent = new Intent(this, StandardUser.class);
        startActivity(intent);
        finish();
    }

    private void startManagerActivity() {
        Intent intent = new Intent(this, ManagerMainPanel.class);
        startActivity(intent);
        finish();
    }

    private void choosePanelByRole(EmployeesEntity employee) {
        Map<Long, Runnable> roleActivities = new HashMap<>();
        roleActivities.put(1L, this::startStandardUserActivity);
        roleActivities.put(2L, this::startManagerActivity);

        Runnable activity = roleActivities.get(employee.getRole());
        if (activity != null) {
            activity.run();
        } else {
            Toast.makeText(appContext.getAppContext(), "Nieznana rola", Toast.LENGTH_SHORT).show();
        }
    }

}

