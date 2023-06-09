package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.MainActivity;
import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ChooseVehicle;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.IRideFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;

import java.util.concurrent.CompletableFuture;

public class ChooseVehiclePanel extends AppCompatActivity {
    private EditText editTextLicensePlate;
    private Button buttonSubmit;
    private ApplicationContextSingleton appContext;
    private IRideFactory rideFactory;

    private ChooseVehicle chooseVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_vehicle_panel);

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        rideFactory = RideFactorySingleton.getInstance().getRideFactory();

        try {
        chooseVehicle = rideFactory.choseVehicle();
        } catch (ContextNotSetException e) {
            throw new RuntimeException(e);
        }

        editTextLicensePlate = findViewById(R.id.editTextLicensePlate);
        buttonSubmit = findViewById(R.id.buttonSubmit);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonSubmitClicked();
            }
        });
    }

    private void onButtonSubmitClicked() {
        String licensePlate = editTextLicensePlate.getText().toString();

        if (licensePlate.isEmpty()) {
            Toast.makeText(appContext.getAppContext(), "Uzupełnij rejestrację", Toast.LENGTH_SHORT).show();
            return;
        }

        CompletableFuture<Boolean> futureResultAvailable = chooseVehicle.isVehicleAvailable(licensePlate);
        futureResultAvailable.thenAcceptAsync(isAvailable -> {
            if (!isAvailable) {
                runOnUiThread(() -> Toast.makeText(appContext.getAppContext(), "Pojazd jest już zajęty", Toast.LENGTH_SHORT).show());
            } else {
                CompletableFuture<Boolean> futureResultBook = chooseVehicle.bookVehicle(licensePlate);
                futureResultBook.thenAcceptAsync(success -> {
                    if (success) {
                        runOnUiThread(() -> Toast.makeText(appContext.getAppContext(), "Pojazd zarezerwowany", Toast.LENGTH_SHORT).show());

                        CompletableFuture<VehiclesEntity> futureResultVehicle = chooseVehicle.getVehicleByPlate(licensePlate);
                        futureResultVehicle.thenAcceptAsync(successVehicle -> {
                            if (successVehicle != null) {
                                RideSession.getInstance().getRide().setVehiclesByVehicle(successVehicle);
                            }
                        });

                        Intent intent = new Intent(this, AddVehicleParametersPanel.class);
                        startActivity(intent);
                        finish();
                    } else {
                        runOnUiThread(() -> Toast.makeText(appContext.getAppContext(), "Błąd", Toast.LENGTH_SHORT).show());
                    }
                });
            }
        });
    }


}

