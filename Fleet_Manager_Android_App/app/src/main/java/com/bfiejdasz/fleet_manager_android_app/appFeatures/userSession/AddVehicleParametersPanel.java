package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.AddVehicleParameterDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ParameterNotSetError;

import java.util.concurrent.CompletableFuture;

public class AddVehicleParametersPanel extends AppCompatActivity {

    private TextView textViewKilometers;
    private EditText editTextKilometers;
    private TextView textViewFuelLevel;
    private EditText editTextFuelLevel;
    private ImageButton buttonNext;
    private ImageButton buttonPrevious;
    private AddVehicleParameterDriver addVehicleParameterDriver;
    private ApplicationContextSingleton appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_vehicle_parameters_panel);

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        addVehicleParameterDriver = new AddVehicleParameterDriver();

        textViewKilometers = findViewById(R.id.textViewKilometers);
        editTextKilometers = findViewById(R.id.editTextKilometers);
        textViewFuelLevel = findViewById(R.id.textViewFuelLevel);
        editTextFuelLevel = findViewById(R.id.editTextFuelLevel);
        buttonNext = findViewById(R.id.nextButton);
        buttonPrevious = findViewById(R.id.previousButton);

        textViewKilometers.setText(getString(R.string.insert_milage_status));
        editTextKilometers.setText(getString(R.string.input_text_hint));
        textViewFuelLevel.setText(getString(R.string.insert_fuel_status));
        editTextFuelLevel.setText(getString(R.string.input_text_hint));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClicked();
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPreviousButtonClicked();
            }
        });
    }

    private void onNextButtonClicked() {
        String kilometers = editTextKilometers.getText().toString();
        String fuelLevel = editTextFuelLevel.getText().toString();

        if(!kilometers.equals("") && !fuelLevel.equals("")) {
            addVehicleParameterDriver.addStartKm(Integer.parseInt(kilometers));
            addVehicleParameterDriver.addStartFuel(Integer.parseInt(fuelLevel));


            try {
                CompletableFuture<Boolean> completableFuture = addVehicleParameterDriver.updateParameters();
                completableFuture.thenAcceptAsync(result -> {
                    if (result) {
                        Intent intent = new Intent(this, CheckInPanel.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(appContext.getAppContext(), "Nieoczekiwany blad", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (ParameterNotSetError e) {
                Toast.makeText(appContext.getAppContext(), "Nieoczekiwany blad", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(appContext.getAppContext(), "Uzupelnij pola", Toast.LENGTH_SHORT).show();
        }
    }

    private void onPreviousButtonClicked() {
        Intent intent = new Intent(this, ChooseVehiclePanel.class);
        startActivity(intent);
        finish();
    }
}
