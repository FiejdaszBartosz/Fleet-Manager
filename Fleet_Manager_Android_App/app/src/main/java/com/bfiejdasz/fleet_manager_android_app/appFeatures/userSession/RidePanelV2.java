package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.IRideFactory;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideLoop;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideLoopDriver;

import java.util.Timer;
import java.util.TimerTask;

public class RidePanelV2 extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView timerTextView;
    private Button stopButton;

    private Context context;

    private UserSession userSession;
    private RideSessionV2 rideSession;
    private CountDownTimer countDownTimer;
    private IRideFactory rideFactory;
    private RideLoop rideLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_panel);

        this.context = this;
        this.userSession = UserSession.getInstance();
        this.rideSession = RideSessionV2.getInstance();
        rideSession.setRide();

        rideFactory = RideFactorySingleton.getInstance().getRideFactory();
        rideFactory.setContext(context);

        try {
            rideLoop = rideFactory.rideLoop();
        } catch (ContextNotSetException e) {
            throw new RuntimeException(e);
        }

        userNameTextView = findViewById(R.id.userName);
        timerTextView = findViewById(R.id.timer);
        stopButton = findViewById(R.id.stopButton);

        userNameTextView.setText(userSession.getEmployee().getName());
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rideLoop.endLoop();
            }
        });

        rideLoop.startLoop();
        startCountdown();
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentTime = rideLoop.getTimeTimer().getCurrentTime();
                String formattedTime = formatTime(currentTime);
                timerTextView.setText(formattedTime);
            }

            @Override
            public void onFinish() {
                rideLoop.endLoop();
            }
        };

        countDownTimer.start();
    }

    private String formatTime(long timeInMillis) {
        int seconds = (int) (timeInMillis / 1000) % 60;
        int minutes = (int) ((timeInMillis / (1000 * 60)) % 60);
        int hours = (int) ((timeInMillis / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
