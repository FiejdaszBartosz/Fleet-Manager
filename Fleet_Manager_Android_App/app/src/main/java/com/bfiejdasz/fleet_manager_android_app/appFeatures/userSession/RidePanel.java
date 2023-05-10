package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;

public class RidePanel extends AppCompatActivity {

    private TextView userNameTextView;
    private TextView timerTextView;
    private Button stopButton;

    private Context context;
    private UserSession userSession;
    private RideSession rideSession;

    private boolean isRunning = false;

    public RidePanel() {
        this.context = this;
        this.userSession = UserSession.getInstance();
        this.rideSession = RideSession.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_panel);

        userNameTextView = findViewById(R.id.userName);
        timerTextView = findViewById(R.id.timer);
        stopButton = findViewById(R.id.stopButton);

        userNameTextView.setText(userSession.getEmployee().getName());

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rideSession.stopTimer();
                isRunning = false;
                Log.i("CZAS", String.valueOf(rideSession.getCurrentTime()));
            }
        });

        this.isRunning = true;
        rideSession.startTimer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted() && isRunning) {
                    final long currentTime = rideSession.getCurrentTime();
                    final String formattedTime = formatTime(currentTime);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timerTextView.setText(formattedTime);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
        rideSession.stopTimer();
        stopThread();
    }

    private void stopThread() {
        final Thread thread = Thread.currentThread();
        if (thread != null) {
            thread.interrupt();
        }
    }

    private void updateTimerTextView() {
        while (isRunning) {
            final long currentTime = rideSession.getCurrentTime();
            final String formattedTime = formatTime(currentTime);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timerTextView.setText(formattedTime);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
