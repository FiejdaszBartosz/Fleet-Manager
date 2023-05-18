package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.timers;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTimer {
    private Timer timer;
    private final long interval;
    private final long startTime;
    private long currentTime;

    public TimeTimer(long startTime) {
        this.interval = 1000;
        this.startTime = startTime;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        }, 0, interval);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void updateTime() {
        currentTime = System.currentTimeMillis() - startTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }
}

