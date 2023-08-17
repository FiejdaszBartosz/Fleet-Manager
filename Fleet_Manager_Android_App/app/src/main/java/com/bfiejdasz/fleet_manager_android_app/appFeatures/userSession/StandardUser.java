package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesEmployeesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.DriverFactory;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandardUser extends AppCompatActivity implements IUser {
    private EmployeesEntity employee;
    private ApplicationContextSingleton appContext;
    private ImageButton startButton;
    private TextView welcomeTextView;
    private TextView greetingTextView;
    private TextView startRideTextView;
    private RidesEmployeesController ridesEmployeesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standar_user_main_panel);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        greetingTextView = findViewById(R.id.additionalText);
        startRideTextView = findViewById(R.id.startText);
        startButton = findViewById(R.id.nextButton);

        this.employee = UserSession.getInstance().getEmployee();
        this.ridesEmployeesController = new RidesEmployeesController();

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        welcomeTextView.setText(getString(R.string.welcome_text_2) + " " + employee.getName());
        greetingTextView.setText(getString(R.string.greeting));
        startRideTextView.setText(getString(R.string.start_ride));

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
        CompletableFuture<Boolean> futureResult = rideSession.setRide();
        futureResult.thenAcceptAsync(result -> {
            if (result) {
                addToRideEmployee();
                Intent intent = new Intent(this, ChooseVehiclePanel.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(appContext.getAppContext(), "Wystąpił błąd", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addToRideEmployee() {
        RidesEmployeesEntity ridesEmployee = new RidesEmployeesEntity();
        ridesEmployee.setIdEmployee(employee.getIdEmployees());
        ridesEmployee.setRideId(RideSession.getInstance().getRide().getRideId());
        ridesEmployee.setEmployeesByIdEmployee(employee);
        List<RidesEmployeesEntity> temp = new ArrayList<>();
        temp.add(ridesEmployee);

        ridesEmployeesController.addRideEmployee(temp, new Callback<List<RidesEmployeesEntity>>() {
            @Override
            public void onResponse(Call<List<RidesEmployeesEntity>> call, Response<List<RidesEmployeesEntity>> response) {
                // do nothing
            }

            @Override
            public void onFailure(Call<List<RidesEmployeesEntity>> call, Throwable t) {
                try {
                    ErrorHandler.handleException(t);
                } catch (Exception e) {
                    ErrorHandler.logWithToastErrors(appContext.getAppContext(), e);
                }
            }
        });
    }
}

