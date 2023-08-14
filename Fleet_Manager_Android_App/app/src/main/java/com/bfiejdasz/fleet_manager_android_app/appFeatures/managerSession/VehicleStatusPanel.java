package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.VehiclesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.ColoredTableView;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.VehicleRepairStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleStatusPanel extends AppCompatActivity {
    private TableLayout tableLayout;
    private List<String> columns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_panel);

        tableLayout = findViewById(R.id.tableLayout);

        VehicleRepairStatus temp = new VehicleRepairStatus();
        columns = temp.getColumns();
        ColoredTableView vehicleTableView = new ColoredTableView(tableLayout, columns);

        VehiclesController vehiclesController = new VehiclesController();

        vehiclesController.getVehicles(new Callback<List<VehiclesEntity>>() {
            @Override
            public void onResponse(Call<List<VehiclesEntity>> call, Response<List<VehiclesEntity>> response) {
                if (response.isSuccessful()) {
                    List<VehiclesEntity> vehiclesList = response.body();
                    for (VehiclesEntity vehicle : vehiclesList) {
                        VehicleRepairStatus repairStatus = new VehicleRepairStatus(vehicle);
                        repairStatus.analyzeRepairStatus().thenRun(() -> {
                            runOnUiThread(() -> vehicleTableView.addRow(repairStatus));
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VehiclesEntity>> call, Throwable t) {
                // Obsługa błędów
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
