package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RepairsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RepairsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRepairPanel extends AppCompatActivity {
    private EditText vehicleEditText;
    private EditText problemEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;
    private Button addRepairButton;
    private RepairsController repairsController;
    private ApplicationContextSingleton appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_repair_panel);

        vehicleEditText = findViewById(R.id.vehicleEditText);
        problemEditText = findViewById(R.id.problemEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        addRepairButton = findViewById(R.id.addRepairButton);

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        repairsController = new RepairsController();

        addRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRepair();
            }
        });
    }

    private void addRepair() {
        String vehicle = vehicleEditText.getText().toString();
        String problem = problemEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String dateString = dateEditText.getText().toString();

        // Walidacja wprowadzonych danych
        if (vehicle.isEmpty() || description.isEmpty() || dateString.isEmpty()) {
            Toast.makeText(this, "Wprowadź wszystkie dane", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tworzenie obiektu naprawy
        RepairsEntity repair = new RepairsEntity();
        repair.setVehicle(Long.parseLong(vehicle));
        if(!problem.isEmpty())
            repair.setProblem(Long.parseLong(problem));
        repair.setDescription(description);
        repair.setComplete((short) 0);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = dateFormat.parse(dateString);
            String formattedDate = dateFormat.format(date);
            repair.setDate(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Nieprawidłowy format daty", Toast.LENGTH_SHORT).show();
            return;
        }


        List<RepairsEntity> temp = new ArrayList<>();
        temp.add(repair);

        repairsController.createRepair(temp, new Callback<List<RepairsEntity>>() {
            @Override
            public void onResponse(Call<List<RepairsEntity>> call, Response<List<RepairsEntity>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddRepairPanel.this, "Naprawa została dodana", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddRepairPanel.this, "Wystąpił błąd podczas dodawania naprawy", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RepairsEntity>> call, Throwable t) {
                try {
                    ErrorHandler.handleException(t);
                } catch (Exception e) {
                    ErrorHandler.logWithToastErrors(appContext.getAppContext(), e);
                }
            }
        });
    }

}
