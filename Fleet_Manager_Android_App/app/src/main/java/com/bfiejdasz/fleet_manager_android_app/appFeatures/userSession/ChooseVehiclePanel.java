package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ChooseVehicle;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.IRideFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;

import java.util.concurrent.CompletableFuture;

public class ChooseVehiclePanel extends AppCompatActivity {
    private EditText editTextLicensePlate;
    private Button buttonSubmit;
    private Context context;
    private IRideFactory rideFactory;

    private ChooseVehicle chooseVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_vehicle_panel);
        this.context = this;
        rideFactory = RideFactorySingleton.getInstance().getRideFactory();
        rideFactory.setContext(context);

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
            Toast.makeText(context, "Uzupełnij rejestrację", Toast.LENGTH_SHORT).show();
            return;
        }

        CompletableFuture<Boolean> futureResultAvailable = chooseVehicle.isVehicleAvailable(licensePlate);
        futureResultAvailable.thenAcceptAsync(isAvailable -> {
            if (!isAvailable) {
                runOnUiThread(() -> Toast.makeText(context, "Pojazd jest już zajęty", Toast.LENGTH_SHORT).show());
            } else {
                CompletableFuture<Boolean> futureResultBook = chooseVehicle.bookVehicle(licensePlate);
                futureResultBook.thenAcceptAsync(success -> {
                    if (success) {
                        runOnUiThread(() -> Toast.makeText(context, "Pojazd zarezerwowany", Toast.LENGTH_SHORT).show());
                    } else {
                        runOnUiThread(() -> Toast.makeText(context, "Błąd", Toast.LENGTH_SHORT).show());
                    }
                });
            }
        });
    }


}

