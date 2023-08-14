package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.PositionsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationPoints {
    private final int id;
    private final PositionsController positionsController;
    private final List<PositionsEntity> positionsEntityList;

    public LocationPoints(int id) {
        this.id = id;
        this.positionsEntityList = new ArrayList<>();
        this.positionsController = new PositionsController();
    }

    public List<PositionsEntity> getPoints() {
        return positionsEntityList;
    }

    public void downloadPoints(ILocationPointsCallback callback) {
        positionsController.getPositionsByRideId(id, new Callback<List<PositionsEntity>>() {
            @Override
            public void onResponse(Call<List<PositionsEntity>> call, Response<List<PositionsEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    positionsEntityList.addAll(response.body());
                    callback.onPointsDownloaded(positionsEntityList);
                } else {
                    callback.onPointsDownloaded(null);
                }
            }

            @Override
            public void onFailure(Call<List<PositionsEntity>> call, Throwable t) {
                callback.onPointsDownloaded(null);
            }
        });
    }
}

