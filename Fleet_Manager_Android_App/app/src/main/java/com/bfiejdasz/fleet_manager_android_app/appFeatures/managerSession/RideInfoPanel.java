package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.TableView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideInfoPanel extends AppCompatActivity {
    private TableLayout tableLayout;
    private RidesController ridesController;
    private final boolean isWhiteBackground = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_panel);

        tableLayout = findViewById(R.id.tableLayout);
        ridesController = new RidesController();

        loadRideData();
    }

    private void loadRideData() {
        ridesController.getRides(new Callback<List<RidesEntity>>() {
            @Override
            public void onResponse(Call<List<RidesEntity>> call, Response<List<RidesEntity>> response) {
                if (response.isSuccessful()) {
                    List<RidesEntity> ridesList = response.body();
                    createTable(ridesList);
                } else {
                    Toast.makeText(RideInfoPanel.this, "Błąd podczas pobierania danych o przejazdach", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RidesEntity>> call, Throwable t) {
                Toast.makeText(RideInfoPanel.this, "Błąd podczas pobierania danych o przejazdach", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createTable(List<RidesEntity> ridesList) {
        if (ridesList.isEmpty()) {
            Toast.makeText(this, "Brak dostępnych przejazdów", Toast.LENGTH_SHORT).show();
            return;
        }

        RidesEntity temp = new RidesEntity();
        List<String> columns = temp.getColumns();

        TableView tableView = new TableView(tableLayout, columns);

        for (RidesEntity ride : ridesList) {
            tableView.addRow(ride);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

