package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.ILocationPointsCallback;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.LocationPoints;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RideTrackerPanel extends AppCompatActivity implements ILocationPointsCallback {
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private TextView rideIdTextView;
    private MapView mapView;
    private List<PositionsEntity> positionsEntityList;
    private ApplicationContextSingleton appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        appContext = ApplicationContextSingleton.getInstance();
        appContext.setAppContext(this);

        setContentView(R.layout.ride_tracker_panel);

        String[] temp = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPermissionsIfNecessary(temp);

        rideIdTextView = findViewById(R.id.rideIdTextView);
        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        mapView.setMultiTouchControls(true);

        Bundle b = getIntent().getExtras();
        int id = b != null ? b.getInt("rideID") : 0;

        setRideId(String.valueOf(id));

        positionsEntityList = new ArrayList<>();

        LocationPoints locationPoints = new LocationPoints(id);
        locationPoints.downloadPoints(this);
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                } else {
                    // Permission denied
                    Toast.makeText(this, "Permission denied: " + permissions[i], Toast.LENGTH_SHORT).show();
                    finish(); // Finish the activity if permissions are not granted
                }
            }
        }
    }

    @Override
    public void onPointsDownloaded(List<PositionsEntity> positions) {
        if (positions != null) {
            showPositionsOnMap(positions);
            positionsEntityList.addAll(positions);
        } else {
            Toast.makeText(appContext.getAppContext(), "Podane RideID nieistnieje", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ManagerMainPanel.class);
            startActivity(intent);
            finish();
        }
    }

    public void setRideId(String rideId) {
        rideIdTextView.setText("Ride ID: " + rideId);
    }

    public void showPositionsOnMap(List<PositionsEntity> positions) {
        List<GeoPoint> geoPointList = convertToGeoPoint(positions);

        for (GeoPoint point : geoPointList) {
            Marker marker = new Marker(mapView);
            marker.setPosition(point);
            mapView.getOverlayManager().add(marker);
        }

        if (!positions.isEmpty()) {
            GeoPoint firstPoint = geoPointList.get(0);
            mapView.getController().setCenter(firstPoint);

            double minLat = firstPoint.getLatitude();
            double maxLat = firstPoint.getLatitude();
            double minLon = firstPoint.getLongitude();
            double maxLon = firstPoint.getLongitude();

            for (GeoPoint point : geoPointList) {
                double lat = point.getLatitude();
                double lon = point.getLongitude();
                minLat = Math.min(minLat, lat);
                maxLat = Math.max(maxLat, lat);
                minLon = Math.min(minLon, lon);
                maxLon = Math.max(maxLon, lon);
            }

            BoundingBox boundingBox = new BoundingBox(maxLat, maxLon, minLat, minLon);
            mapView.zoomToBoundingBox(boundingBox, true);

            mapView.invalidate();
        }
    }

    private List<GeoPoint> convertToGeoPoint(List<PositionsEntity> positionsEntityList) {
        return positionsEntityList.stream()
                .map(temp -> new GeoPoint(temp.getyCord(), temp.getxCord()))
                .collect(Collectors.toList());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
