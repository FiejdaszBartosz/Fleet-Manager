package com.bfiejdasz.fleet_manager_android_app.appFeatures;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.LoginPanel;

public class PermissionsActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS_CODE = 1;

    private TextView titleTextView;
    private Button grantPermissionsButton;

    private final String[] requiredPermissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        titleTextView = findViewById(R.id.welcomeTextView);
        grantPermissionsButton = findViewById(R.id.grantPermissionsButton);

        titleTextView.setText(getString(R.string.welcome_text));
        grantPermissionsButton.setText(getString(R.string.permision_button));

        checkPermissions();

        grantPermissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });
    }

    private void checkPermissions() {
        boolean allPermissionsGranted = true;
        for (String permission : requiredPermissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (allPermissionsGranted) {
            redirectToMainActivity();
        } else {
            showPermissionsDialog();
        }
    }

    private void showPermissionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wymagane pozwolenia");
        builder.setMessage("Aplikacja wymaga dostępu do niektórych funkcji. Wyrażasz zgodę na udzielenie wymaganych pozwoleni?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(PermissionsActivity.this, requiredPermissions, REQUEST_PERMISSIONS_CODE);
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                redirectToMainActivity();
            } else {
                showSettingsDialog();
            }
        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wymagane pozwolenia");
        builder.setMessage("Aplikacja wymaga dostępu do niektórych funkcji. Przejdź do ustawień aplikacji, aby udzielić pozwolenia.");
        builder.setPositiveButton("Przejdź do ustawień", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAppSettings();
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });

        // Dodaj szczegóły dotyczące każdego wymaganego uprawnienia
        StringBuilder permissionsDetails = new StringBuilder();
        permissionsDetails.append("Wymagane pozwolenia:\n");
        for (String permission : requiredPermissions) {
            if (ContextCompat.checkSelfPermission(PermissionsActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsDetails.append("- ").append(permission).append("\n");
            }
        }
        builder.setMessage(permissionsDetails.toString());

        builder.show();
    }


    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void redirectToMainActivity() {
        Intent intent = new Intent(this, LoginPanel.class);
        startActivity(intent);
        finish();
    }
}


