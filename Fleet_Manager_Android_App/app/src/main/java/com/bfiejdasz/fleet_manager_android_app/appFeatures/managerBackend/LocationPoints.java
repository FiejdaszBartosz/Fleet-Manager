package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.PositionsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationPoints {
    private int id;
    private List<GeoPoint> points;
    private PositionsController positionsController;

    public LocationPoints(int id) {
        this.id = id;
        this.points = new ArrayList<>();
        this.positionsController = new PositionsController();
    }

    private void addPoint(double latitude, double longitude) {
        GeoPoint point = new GeoPoint(latitude, longitude);
        points.add(point);
    }

    public List<GeoPoint> getPoints() {
        return points;
    }

    public void downloadPoints(ILocationPointsCallback callback) {
        positionsController.getPositionsByRideId(id, new Callback<List<PositionsEntity>>() {
            @Override
            public void onResponse(Call<List<PositionsEntity>> call, Response<List<PositionsEntity>> response) {
                if (response.isSuccessful()) {
                    for (PositionsEntity temp : response.body()) {
                        addPoint(temp.getyCord(), temp.getxCord());
                    }
                    callback.onPointsDownloaded(points);
                }
            }

            @Override
            public void onFailure(Call<List<PositionsEntity>> call, Throwable t) {
                // Obsługa błędu
            }
        });
    }
}

