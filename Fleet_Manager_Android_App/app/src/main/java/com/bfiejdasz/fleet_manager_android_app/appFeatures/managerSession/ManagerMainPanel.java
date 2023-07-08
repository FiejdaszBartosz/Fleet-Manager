package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.IUser;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.UserSession;

public class ManagerMainPanel extends AppCompatActivity implements IUser {
    private TextView welcomeTextView;
    private TextView userNameTextView;
    private Button rideListButton;
    private Button ridePreviewButton;
    private Button addRepairButton;
    private EmployeesEntity employee;
    private ApplicationContextSingleton appContext;
    private String dialogInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main_panel);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        userNameTextView = findViewById(R.id.userNameTextView);
        rideListButton = findViewById(R.id.rideListButton);
        ridePreviewButton = findViewById(R.id.ridePreviewButton);
        addRepairButton = findViewById(R.id.addRepairButtonToPanel);

        this.employee = UserSession.getInstance().getEmployee();

        userNameTextView.setText(employee.getName());

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        rideListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRideList();
            }
        });

        ridePreviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogID();
            }
        });

        addRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddRepairPreview();
            }
        });
    }

    private void dialogID() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Podaj RideID");
        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogInput = input.getText().toString();
                if (!dialogInput.equals(""))
                    openRidePreview(Integer.parseInt(dialogInput));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void openRideList() {
        Toast.makeText(appContext.getAppContext(), "Otwórz listę przejazdów", Toast.LENGTH_SHORT).show();
        // Dodaj kod obsługi otwierania listy przejazdów
    }

    private void openRidePreview(int id) {
        Toast.makeText(appContext.getAppContext(), "Otwórz podgląd przejazdu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RideTrackerPanel.class);
        intent.putExtra("rideID", id);
        startActivity(intent);
        finish();
    }

    private void openAddRepairPreview() {
        Toast.makeText(appContext.getAppContext(), "Otwórz podgląd przejazdu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddRepairPanel.class);
        startActivity(intent);
        finish();
    }

    @Override
    public EmployeesEntity getUser() {
        return this.employee;
    }
}

