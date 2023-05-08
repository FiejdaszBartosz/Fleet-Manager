package com.bfiejdasz.fleet_manager_android_app.locationsFeatures;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;

import androidx.core.app.ActivityCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;


public class LocationProvider implements ILocationProvider {
    private final Context context;
    private MapView mapView;

    public LocationProvider(Context context) {
        this.context = context;
        Configuration.getInstance().load(context, androidx.preference.PreferenceManager.getDefaultSharedPreferences(context));
    }

    public void askForPermissions() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public Location getCurrentLocation() {
        mapView = new MapView(context);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        MyLocationNewOverlay myLocationOverlay = new MyLocationNewOverlay(mapView);
        myLocationOverlay.enableMyLocation();

        mapView.getOverlays().add(myLocationOverlay);

        GeoPoint startPoint = myLocationOverlay.getMyLocation();
        if (startPoint != null) {
            Location location = new Location("OpenStreetMap");
            location.setLatitude(startPoint.getLatitude());
            location.setLongitude(startPoint.getLongitude());
            return location;
        } else {
            return null;
        }
    }

    public void getLocationsWithInterval(List<Location> locations, long timeInterval) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Location currentLocation = getCurrentLocation();
                if (currentLocation != null) {
                    locations.add(currentLocation);
                }
                handler.postDelayed(this, timeInterval * 1000);
            }
        }, timeInterval * 1000);
    }
}
