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
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.AddVehicleParameterDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ParameterNotSetError;

public class AddVehicleParametersPanel extends AppCompatActivity {

    private TextView textViewKilometers;
    private EditText editTextKilometers;
    private TextView textViewFuelLevel;
    private EditText editTextFuelLevel;
    private Button buttonNext;
    private AddVehicleParameterDriver addVehicleParameterDriver;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_vehicle_parameters_panel);
        this.context = this;
        RideFactorySingleton.getInstance().getRideFactory().setContext(context);

        addVehicleParameterDriver = new AddVehicleParameterDriver();
        addVehicleParameterDriver.setContext(context);

        textViewKilometers = findViewById(R.id.textViewKilometers);
        editTextKilometers = findViewById(R.id.editTextKilometers);
        textViewFuelLevel = findViewById(R.id.textViewFuelLevel);
        editTextFuelLevel = findViewById(R.id.editTextFuelLevel);
        buttonNext = findViewById(R.id.buttonNext);

        textViewKilometers.setText("Wpisz liczbę kilometrów:");
        textViewFuelLevel.setText("Wpisz stan paliwa:");

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
            addVehicleParameterDriver.addStartKm(Integer.parseInt(kilometers));
            addVehicleParameterDriver.addStartFuel(Integer.parseInt(fuelLevel));

            try {
                addVehicleParameterDriver.updateParameters();
                Intent intent = new Intent(this, CheckInPanel.class);
                startActivity(intent);
                finish();
            } catch (ParameterNotSetError e) {
                Toast.makeText(context, "Nieoczekiwany blad", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Uzupelnij pola", Toast.LENGTH_SHORT).show();
        }
    }
}
