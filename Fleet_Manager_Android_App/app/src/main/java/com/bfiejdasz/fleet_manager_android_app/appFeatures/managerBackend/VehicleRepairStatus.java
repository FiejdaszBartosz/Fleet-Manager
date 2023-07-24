package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import android.graphics.Color;

import com.bfiejdasz.fleet_manager_android_app.api.ITableItem;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.PlanedRepairsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PlanedRepairsEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.VehiclesEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRepairStatus implements ITableItem {
    private VehiclesEntity vehicle;
    private String color;
    private String description;

    public VehicleRepairStatus(VehiclesEntity vehicle) {
        this.vehicle = vehicle;
        this.color = "Green";
        this.description = "No immediate repairs needed.";
        analyzeRepairStatus();
    }

    public VehicleRepairStatus() {}

    public VehiclesEntity getVehicle() {
        return vehicle;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public CompletableFuture<Void> analyzeRepairStatus() {
        int mileage = vehicle.getMileage();
        Date today = new Date();

        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;

        CompletableFuture<Collection<PlanedRepairsEntity>> future = vehicle.getPlanedRepairsByIdVehicles();

        return future.thenAccept(planedRepairs -> {
            boolean needsRedRepair = false;
            boolean needsYellowRepair = false;

            if (planedRepairs != null) {
                for (PlanedRepairsEntity plannedRepair : planedRepairs) {
                    if (plannedRepair != null) {
                        long daysDifference = (plannedRepair.getTime().getTime() - today.getTime()) / oneDayInMilliseconds;

                        if (plannedRepair.getKm() <= mileage + 1000 || daysDifference <= 15) {
                            needsRedRepair = true;
                            break;
                        }

                        if (plannedRepair.getKm() <= mileage + 5000 || daysDifference <= 60) {
                            needsYellowRepair = true;
                            break;
                        }
                    }
                }
            }

            if (needsRedRepair) {
                color = "Red";
                description = "Vehicle requires repair within 1000 km or 15 days.";
            } else if (needsYellowRepair) {
                color = "Yellow";
                description = "Vehicle requires repair within 5000 km or 60 days.";
            } else {
                color = "Green";
                description = "No immediate repairs needed.";
            }
        }).exceptionally(e -> {
            return null;
        });
    }

    @Override
    public List<String> getColumns() {
        List<String> columns = new ArrayList<>();
        columns.add("Status");
        columns.add("ID");
        columns.add("Manufacturer");
        columns.add("Model");
        columns.add("VIN");
        columns.add("License Plate");
        columns.add("Mileage");
        columns.add("Description");
        return columns;
    }

    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(vehicle.getIdVehicles()));
        values.add(getValueOrNotSet(vehicle.getManufacture()));
        values.add(getValueOrNotSet(vehicle.getModel()));
        values.add(getValueOrNotSet(vehicle.getVin()));
        values.add(getValueOrNotSet(vehicle.getLicensePlate()));
        values.add(getValueOrNotSet(String.valueOf(vehicle.getMileage())));
        values.add(description);
        return values;
    }

    private String getValueOrNotSet(String value) {
        return value != null && !value.isEmpty() ? value : "Not Set";
    }

    public int getStatusColor() {
        switch (color) {
            case "Red":
                return Color.RED;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.GREEN;
        }
    }
}

