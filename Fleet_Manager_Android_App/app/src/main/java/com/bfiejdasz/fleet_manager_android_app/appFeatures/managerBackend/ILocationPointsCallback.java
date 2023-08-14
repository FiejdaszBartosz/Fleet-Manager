package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;

import java.util.List;

public interface ILocationPointsCallback {
    void onPointsDownloaded(List<PositionsEntity> points);
}

