package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RepairsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RepairsEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepairStatusPanel extends AppCompatActivity {
    private Spinner repairSpinner;
    private TextView titleLabel;
    private TextView idRepairEditText;
    private TextView idVehicleEditText;
    private TextView idProblemEditText;
    private TextView statusEditText;
    private TextView dateEditText;
    private Button completeRepairButton;
    private TextView repairIDTextView;
    private TextView vehicleIDTextView;
    private TextView problemIDTextView;
    private TextView statusTextView;
    private TextView dateTextView;
    private RepairsController repairsController;
    private List<RepairsEntity> repairsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_status_panel);

        titleLabel = findViewById(R.id.titleLabel);
        repairSpinner = findViewById(R.id.repairSpinner);
        idRepairEditText = findViewById(R.id.idRepairEditText);
        idVehicleEditText = findViewById(R.id.idVehicleEditText);
        idProblemEditText = findViewById(R.id.idProblemEditText);
        statusEditText = findViewById(R.id.statusEditText);
        dateEditText = findViewById(R.id.dateEditText);
        completeRepairButton = findViewById(R.id.completeRepairButton);

        repairIDTextView = findViewById(R.id.repairID);
        vehicleIDTextView = findViewById(R.id.vehicleID);
        problemIDTextView = findViewById(R.id.problemID);
        statusTextView = findViewById(R.id.statusTextView);
        dateTextView = findViewById(R.id.dateTextView);

        repairIDTextView.setText(getString(R.string.label_repair_id));
        vehicleIDTextView.setText(getString(R.string.label_vehicle_id));
        problemIDTextView.setText(getString(R.string.label_problem_id));
        statusTextView.setText(getString(R.string.label_status));
        dateTextView.setText(getString(R.string.label_date));
        completeRepairButton.setText(getString(R.string.button_complete_repair));

        repairsController = new RepairsController();

        loadRepairsData();
        setupCompleteRepairButton();
    }

    private void loadRepairsData() {
        repairsController.getRepairs(new Callback<List<RepairsEntity>>() {
            @Override
            public void onResponse(Call<List<RepairsEntity>> call, Response<List<RepairsEntity>> response) {
                if (response.isSuccessful()) {
                    repairsList = response.body();
                    populateSpinner();
                } else {
                    Toast.makeText(RepairStatusPanel.this, "Błąd podczas pobierania danych napraw", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RepairsEntity>> call, Throwable t) {
                Toast.makeText(RepairStatusPanel.this, "Błąd podczas pobierania danych napraw", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateSpinner() {
        List<String> repairIds = new ArrayList<>();
        for (RepairsEntity repair : repairsList) {
            repairIds.add(String.valueOf(repair.getIdRepairs()));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repairIds);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repairSpinner.setAdapter(spinnerAdapter);

        repairSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedRepairIndex = repairSpinner.getSelectedItemPosition();
                if (selectedRepairIndex >= 0 && selectedRepairIndex < repairsList.size()) {
                    RepairsEntity selectedRepair = repairsList.get(selectedRepairIndex);
                    displayRepairDetails(selectedRepair);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }

    private void displayRepairDetails(RepairsEntity repair) {
        idRepairEditText.setText(String.valueOf(repair.getIdRepairs()));
        idVehicleEditText.setText(String.valueOf(repair.getVehicle()));
        idProblemEditText.setText(String.valueOf(repair.getProblem()));
        statusEditText.setText(String.valueOf(repair.getComplete()));
        dateEditText.setText(String.valueOf(repair.getDate()));
    }

    private void setupCompleteRepairButton() {
        completeRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeRepair();
            }
        });
    }

    private void completeRepair() {
        int selectedRepairIndex = repairSpinner.getSelectedItemPosition();
        if (selectedRepairIndex >= 0 && selectedRepairIndex < repairsList.size()) {
            RepairsEntity selectedRepair = repairsList.get(selectedRepairIndex);
            selectedRepair.setComplete((short) 1);

            repairsController.updateRepairById((int) selectedRepair.getIdRepairs(), selectedRepair, new Callback<RepairsEntity>() {
                @Override
                public void onResponse(Call<RepairsEntity> call, Response<RepairsEntity> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RepairStatusPanel.this, "Naprawa zakończona", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RepairStatusPanel.this, "Błąd podczas aktualizacji naprawy", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RepairsEntity> call, Throwable t) {
                    Toast.makeText(RepairStatusPanel.this, "Błąd podczas aktualizacji naprawy", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
