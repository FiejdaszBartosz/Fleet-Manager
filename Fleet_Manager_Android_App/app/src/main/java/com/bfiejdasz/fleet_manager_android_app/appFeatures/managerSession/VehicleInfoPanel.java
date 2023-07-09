package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.VehiclesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.TableView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleInfoPanel extends AppCompatActivity {
    private TableLayout tableLayout;
    private VehiclesController vehiclesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_panel);

        tableLayout = findViewById(R.id.tableLayout);
        vehiclesController = new VehiclesController();

        loadVehicleData();
    }

    private void loadVehicleData() {
        vehiclesController.getVehicles(new Callback<List<VehiclesEntity>>() {
            @Override
            public void onResponse(Call<List<VehiclesEntity>> call, Response<List<VehiclesEntity>> response) {
                if (response.isSuccessful()) {
                    List<VehiclesEntity> vehiclesList = response.body();
                    createTable(vehiclesList);
                } else {
                    Toast.makeText(VehicleInfoPanel.this, "Błąd podczas pobierania danych pojazdów", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<VehiclesEntity>> call, Throwable t) {
                Toast.makeText(VehicleInfoPanel.this, "Błąd podczas pobierania danych pojazdów", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createTable(List<VehiclesEntity> vehiclesList) {
        if (vehiclesList.isEmpty()) {
            Toast.makeText(this, "Brak dostępnych pojazdów", Toast.LENGTH_SHORT).show();
            return;
        }

        VehiclesEntity temp = new VehiclesEntity();
        List<String> columns = temp.getColumns();

        TableView tableView = new TableView(tableLayout, columns);

        for (VehiclesEntity vehicle : vehiclesList) {
            tableView.addRow(vehicle);
        }
    }
}

