package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ChooseVehicle;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.IRideFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;

import java.util.concurrent.CompletableFuture;

public class ChooseVehiclePanel extends AppCompatActivity {

    private TextView licensePlateTextView;
    private EditText editTextLicensePlate;
    private ImageButton nextButton;
    private ImageButton previousButton;
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

        licensePlateTextView = findViewById(R.id.licensePlateTextView);
        editTextLicensePlate = findViewById(R.id.editTextLicensePlate);
        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);

        licensePlateTextView.setText(getString(R.string.license_plate_edit_text));
        editTextLicensePlate.setText(getString(R.string.input_text_hint));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonNextClicked();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPreviousClicked();
            }
        });
    }

    private void onButtonNextClicked() {
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
                                RideSession temp = RideSession.getInstance();
                                temp.getRide().setVehicle(successVehicle.getIdVehicles());
                                temp.getRide().setVehiclesByVehicle(successVehicle);
                                temp.updateRide();
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

    private void onButtonPreviousClicked() {
        Intent intent = new Intent(this, StandardUser.class);
        startActivity(intent);
        finish();
    }

}

