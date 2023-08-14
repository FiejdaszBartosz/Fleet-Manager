package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.EndRideDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ParameterNotSetError;

public class EndRidePanel extends AppCompatActivity {
    private TextView textViewKilometers;
    private EditText editTextKilometers;
    private TextView textViewFuelLevel;
    private EditText editTextFuelLevel;
    private Button buttonNext;
    private EndRideDriver endRideDriver;
    private ApplicationContextSingleton appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_vehicle_parameters_panel);

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        endRideDriver = new EndRideDriver();

        textViewKilometers = findViewById(R.id.textViewKilometers);
        editTextKilometers = findViewById(R.id.editTextKilometers);
        textViewFuelLevel = findViewById(R.id.textViewFuelLevel);
        editTextFuelLevel = findViewById(R.id.editTextFuelLevel);
        buttonNext = findViewById(R.id.nextButton);

        textViewKilometers.setText("Wpisz stan licznika:");
        textViewFuelLevel.setText("Wpisz koncowy stan paliwa:");

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClicked();
            }
        });
    }

    private void onNextButtonClicked() {
        String kilometers = editTextKilometers.getText().toString();
        String fuelLevel = editTextFuelLevel.getText().toString();

        if(!kilometers.equals("") && !fuelLevel.equals("")) {
            endRideDriver.addStopKm(Integer.parseInt(kilometers));
            endRideDriver.addStopFuel(Integer.parseInt(fuelLevel));
            endRideDriver.setStopTime();

            try {
                endRideDriver.updateParameters();
                endRideDriver.freeVehicle();
                endRideDriver.updateVehicle(Integer.parseInt(kilometers));
            } catch (ParameterNotSetError e) {
                Toast.makeText(appContext.getAppContext(), "Nieoczekiwany blad", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(appContext.getAppContext(), "Uzupelnij pola", Toast.LENGTH_SHORT).show();
        }
    }
}
