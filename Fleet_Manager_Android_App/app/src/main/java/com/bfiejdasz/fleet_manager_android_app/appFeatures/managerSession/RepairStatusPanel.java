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
    private TextView repairDetailsTextView;
    private Button completeRepairButton;
    private RepairsController repairsController;
    private List<RepairsEntity> repairsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_status_panel);

        repairSpinner = findViewById(R.id.repairSpinner);
        repairDetailsTextView = findViewById(R.id.repairDetailsTextView);
        completeRepairButton = findViewById(R.id.completeRepairButton);
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
        String details = "ID: " + repair.getIdRepairs() + "\n"
                + "Vehicle: " + repair.getVehicle() + "\n"
                + "Problem: " + repair.getProblem() + "\n"
                + "Description: " + repair.getDescription() + "\n"
                + "Complete: " + repair.getComplete() + "\n"
                + "Date: " + repair.getDate();

        repairDetailsTextView.setText(details);
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