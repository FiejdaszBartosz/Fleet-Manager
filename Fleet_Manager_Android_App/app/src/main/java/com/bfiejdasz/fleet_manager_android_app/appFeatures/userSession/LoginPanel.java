package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.EmployeesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPanel extends AppCompatActivity {
    private Context context;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_panel);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        this.context = this;
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
            Toast.makeText(context, "Uzupełnij login i hasło", Toast.LENGTH_SHORT).show();
        } else {

            EmployeesController employeesController = new EmployeesController();
            employeesController.checkCredentials(login, password, new Callback<EmployeesEntity>() {
                @Override
                public void onResponse(Call<EmployeesEntity> call, Response<EmployeesEntity> response) {
                    if (response.isSuccessful()) {
                        userSession.login(response.body());
                        startStandardUserActivity();
                    } else {
                        Toast.makeText(context, "Nieoczekiwany błąd", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EmployeesEntity> call, Throwable t) {
                    try {
                        ErrorHandler.handleException(t);
                    } catch (Exception e) {
                        ErrorHandler.logWithToastErrors(context, e);
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
}

