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
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.RidesController;

import java.util.Timer;
import java.util.TimerTask;

public class RidePanel extends AppCompatActivity {

    private TextView userNameTextView;
    private TextView timerTextView;
    private Button stopButton;

    private Context context;
    private UserSession userSession;
    private RideSession rideSession;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_panel);

        this.context = this;
        this.userSession = UserSession.getInstance();
        this.rideSession = RideSession.getInstance();
        rideSession.setContext(context);
        rideSession.setAll();

        userNameTextView = findViewById(R.id.userName);
        timerTextView = findViewById(R.id.timer);
        stopButton = findViewById(R.id.stopButton);

        userNameTextView.setText(userSession.getEmployee().getName());
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rideSession.timeTimer.stop();
                rideSession.locationTimer.stop();
                Log.i("CZAS", String.valueOf(rideSession.timeTimer.getCurrentTime()));
            }
        });

        rideSession.timeTimer.start();
        startCountdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                rideSession.locationTimer.start();
            }
        }, 5000);
    }

    private String formatTime(long timeInMillis) {
        int seconds = (int) (timeInMillis / 1000) % 60;
        int minutes = (int) ((timeInMillis / (1000 * 60)) % 60);
        int hours = (int) ((timeInMillis / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rideSession.timeTimer.stop();
        rideSession.locationTimer.stop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentTime = rideSession.timeTimer.getCurrentTime();
                String formattedTime = formatTime(currentTime);
                timerTextView.setText(formattedTime);
            }

            @Override
            public void onFinish() {
                rideSession.timeTimer.stop();
            }
        };

        countDownTimer.start();
    }
}

