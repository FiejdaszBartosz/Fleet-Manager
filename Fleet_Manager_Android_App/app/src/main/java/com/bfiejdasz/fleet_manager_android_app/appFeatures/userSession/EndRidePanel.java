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
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.AddVehicleParameterDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.EndRideDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ParameterNotSetError;

public class EndRidePanel extends AppCompatActivity {
    private TextView textViewKilometers;
    private EditText editTextKilometers;
    private TextView textViewFuelLevel;
    private EditText editTextFuelLevel;
    private Button buttonNext;
    private EndRideDriver endRideDriver;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_vehicle_parameters_panel);
        this.context = this;
        RideFactorySingleton.getInstance().getRideFactory().setContext(context);

        endRideDriver = new EndRideDriver();

        textViewKilometers = findViewById(R.id.textViewKilometers);
        editTextKilometers = findViewById(R.id.editTextKilometers);
        textViewFuelLevel = findViewById(R.id.textViewFuelLevel);
        editTextFuelLevel = findViewById(R.id.editTextFuelLevel);
        buttonNext = findViewById(R.id.buttonNext);

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
                Toast.makeText(context, "Nieoczekiwany blad", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Uzupelnij pola", Toast.LENGTH_SHORT).show();
        }
    }
}