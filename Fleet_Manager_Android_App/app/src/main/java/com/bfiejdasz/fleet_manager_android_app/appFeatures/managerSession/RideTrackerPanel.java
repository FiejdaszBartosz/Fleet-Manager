package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.ILocationPointsCallback;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.LocationPoints;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RideTrackerPanel extends AppCompatActivity implements ILocationPointsCallback, OnMapReadyCallback {

    private final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private TextView rideIdTextView;
    private GoogleMap googleMap;
    private List<PositionsEntity> positionsEntityList;
    private ApplicationContextSingleton appContext;
    private int rideID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_tracker_panel);

        rideIdTextView = findViewById(R.id.rideIdTextView);
        positionsEntityList = new ArrayList<>();
        appContext = ApplicationContextSingleton.getInstance();
        appContext.setAppContext(this);

        Bundle b = getIntent().getExtras();
        rideID = b != null ? b.getInt("rideID") : 0;

        setRideId(String.valueOf(rideID));

        // Inicjalizacja mapy Google
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
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

    private void showPositionsOnMap(List<PositionsEntity> positions) {
        if (googleMap != null && !positions.isEmpty()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (PositionsEntity position : positions) {
                Double xCord = position.getxCord();
                Double yCord = position.getyCord();

                Log.d("LOKALIZACJA", "xCord: " + xCord + ", yCord: " + yCord);

                if (xCord != null && yCord != null) {
                    LatLng positionLatLng = new LatLng(yCord, xCord);

                    googleMap.addMarker(new MarkerOptions().position(positionLatLng).title("Pinezka"));
                    builder.include(positionLatLng); // Dodaj punkt do obszaru wyznaczania granic mapy
                }
            }

            LatLngBounds bounds = builder.build();
            int padding = 100; // Odstęp od krawędzi widoku
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);

            googleMap.moveCamera(cameraUpdate);
        }
    }

    public void setRideId(String rideId) {
        rideIdTextView.setText("Ride ID: " + rideId);
    }

    private void requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Jeśli uprawnienia nie zostały jeszcze udzielone, poproś o nie
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            // Sprawdź, czy użytkownik przyznał uprawnienia
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Uprawnienia zostały przyznane, ale nie uruchamiamy loadGoogleMap() tutaj
            } else {
                // Jeśli użytkownik odrzucił uprawnienia, możesz wyświetlić komunikat lub podjąć odpowiednie działania
                Toast.makeText(this, "Brak uprawnień do dostępu do lokalizacji.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        requestLocationPermissions();

        LocationPoints locationPoints = new LocationPoints(rideID);
        locationPoints.downloadPoints(this);
    }

    private void loadGoogleMap() {
        // Tutaj możesz dodać kod do ładowania mapy Google
        if (googleMap != null) {
            // Przykład: Usunięcie wstępnego markeru
            googleMap.clear();

            // Przykład: Dodać pinezki z pobranych danych
            for (PositionsEntity position : positionsEntityList) {
                Double xCord = position.getxCord();
                Double yCord = position.getyCord();

                if (xCord != null && yCord != null) {
                    LatLng positionLatLng = new LatLng(yCord, xCord);
                    googleMap.addMarker(new MarkerOptions().position(positionLatLng).title("Pinezka"));
                }
            }

            // Przykład: Jeśli istnieją pinezki, wyznaczenie granic i przybliżenie
            if (!positionsEntityList.isEmpty()) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (PositionsEntity position : positionsEntityList) {
                    Double xCord = position.getxCord();
                    Double yCord = position.getyCord();
                    if (xCord != null && yCord != null) {
                        LatLng positionLatLng = new LatLng(yCord, xCord);
                        builder.include(positionLatLng);
                    }
                }
                LatLngBounds bounds = builder.build();
                int padding = 100; // Odstęp od krawędzi widoku
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                googleMap.moveCamera(cameraUpdate);
            }
        }
    }
}
