package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1; // Dodaj stałą dla żądania dostępu do lokalizacji
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

        // Poproś użytkownika o uprawnienia do lokalizacji
        requestLocationPermissions();

        setContentView(R.layout.ride_tracker_panel);

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

    public void onResume(){
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    public void onPause(){
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().save(this, prefs);
    }

    @Override
    public void onPointsDownloaded(List<PositionsEntity> positions) {
        if (positions != null) {
            showPositionsOnMap(positions);
            positionsEntityList.addAll(positions);
        } else {
            Toast.makeText(appContext.getAppContext(), "Podane RideID nie istnieje", Toast.LENGTH_SHORT).show();
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

    private void requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Jeśli uprawnienia nie zostały jeszcze udzielone, poproś o nie
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            // Sprawdź, czy użytkownik przyznał uprawnienia
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Jeśli użytkownik przyznał uprawnienia, możesz kontynuować działanie, np. ładowanie mapy
                // Tutaj możesz umieścić kod do ładowania mapy
            } else {
                // Jeśli użytkownik odrzucił uprawnienia, możesz wyświetlić komunikat lub podjąć odpowiednie działania
                Toast.makeText(this, "Brak uprawnień do dostępu do lokalizacji.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
