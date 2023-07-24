package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.ILocationPointsCallback;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend.LocationPoints;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RideTrackerPanel extends AppCompatActivity implements ILocationPointsCallback {
    private TextView rideIdTextView;
    private MapView mapView;
    private List<PositionsEntity> positionsEntityList;
    private ApplicationContextSingleton appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_tracker_panel);

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        rideIdTextView = findViewById(R.id.rideIdTextView);
        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));

        Bundle b = getIntent().getExtras();
        int id = b.getInt("rideID");

        setRideId(b.toString());

        positionsEntityList = new ArrayList<>();

        LocationPoints locationPoints = new LocationPoints(id);
        locationPoints.downloadPoints(this);
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

        List<OverlayItem> overlayItems = createOverlayItems(geoPointList);
        ItemizedOverlayWithFocus<OverlayItem> itemOverlay = createItemizedOverlay(overlayItems);
        mapView.getOverlays().add(itemOverlay);

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
                if (lat < minLat) {
                    minLat = lat;
                }
                if (lat > maxLat) {
                    maxLat = lat;
                }
                if (lon < minLon) {
                    minLon = lon;
                }
                if (lon > maxLon) {
                    maxLon = lon;
                }
            }

            BoundingBox boundingBox = new BoundingBox(maxLat, maxLon, minLat, minLon);
            mapView.zoomToBoundingBox(boundingBox, true);

            mapView.invalidate();
        }
    }

    private List<OverlayItem> createOverlayItems(List<GeoPoint> positions) {
        List<OverlayItem> overlayItems = new ArrayList<>();
        for (GeoPoint point : positions) {
            OverlayItem overlayItem = new OverlayItem("Punkt", "Opis punktu", point);
            overlayItems.add(overlayItem);
        }
        return overlayItems;
    }

    private ItemizedOverlayWithFocus<OverlayItem> createItemizedOverlay(List<OverlayItem> overlayItems) {
        return new ItemizedOverlayWithFocus<>(
                overlayItems,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                },
                getApplicationContext()
        );
    }

    private List<GeoPoint> convertToGeoPoint(List<PositionsEntity> positionsEntityList) {
        return positionsEntityList.stream()
                .map(temp -> new GeoPoint(temp.getyCord(), temp.getxCord()))
                .collect(Collectors.toList());
    }

}
