package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers;

import android.location.Location;

import com.bfiejdasz.fleet_manager_android_app.api.CustomHttpException;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.PositionsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_android_app.api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.TimestampGenerator;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;
import com.bfiejdasz.fleet_manager_android_app.exceptions.UnexpectedErrorException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.BadRequestException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.ForbiddenException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.NetworkException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.NotFoundException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.ServerErrorException;
import com.bfiejdasz.fleet_manager_android_app.exceptions.apiExceptions.UnauthorizedException;
import com.bfiejdasz.fleet_manager_android_app.locationsFeatures.LocationProviderProxy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationTimer {
    private Timer timer;
    private long interval;
    private RidesEntity ridesEntity;
    private LocationProviderProxy locationProvider;
    private final long rideID;

    private PositionsController positionsController;

    public LocationTimer(RidesEntity ridesEntity, LocationProviderProxy locationProvider, long interval) {
        this.ridesEntity = ridesEntity;
        this.locationProvider = locationProvider;
        this.interval = interval;

        this.rideID = ridesEntity.getRideId();

        this.positionsController = new PositionsController();
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<PositionsEntity> temp = setPosition();
                if (temp.get(0) != null)
                    sendLocation(temp);
            }
        }, 0, interval);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private List<PositionsEntity> setPosition() {
        PositionsEntity position = null;
        Location temp = locationProvider.getCurrentLocation();
        if (temp != null) {
            position = new PositionsEntity();
            position.setRideid(rideID);
            position.setTime(String.valueOf(LocalDateTime.now()));
            position.setxCord(temp.getLongitude());
            position.setyCord(temp.getLatitude());
        }
        List<PositionsEntity> temp_list = new ArrayList<>();
        temp_list.add(position);
        return temp_list;
    }

    private void sendLocation(List<PositionsEntity> position) {
        positionsController.createPosition(position, new Callback<List<PositionsEntity>>() {
            @Override
            public void onResponse(Call<List<PositionsEntity>> call, Response<List<PositionsEntity>> response) {
                // do nothing
            }

            @Override
            public void onFailure(Call<List<PositionsEntity>> call, Throwable t) {
                try {
                    ErrorHandler.handleException(t);
                } catch (Exception e) {
                    ErrorHandler.logErrors(e);
                }
            }
        });
    }

}


