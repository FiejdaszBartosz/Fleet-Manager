package com.bfiejdasz.fleet_manager_android_app;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession.ManagerMainPanel;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession.RideInfoPanel;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.managerSession.VehicleInfoPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ManagerMainPanelTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(ManagerMainPanel.class);
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testActivityLaunch() {
        ActivityScenario<ManagerMainPanel> scenario = ActivityScenario.launch(ManagerMainPanel.class);
        assertNotNull(scenario);
    }

    @Test
    public void testOpenRidePreviewButton() {
        onView(withId(R.id.ridePreviewButton)).perform(click());
        onView(withText("Podaj RideID")).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenManagerRepairsPanelButton() {
        onView(withId(R.id.repairsPanelButtonToPanel)).perform(click());
        onView(withText("Otwórz panel napraw")).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenVehiclesTableButton() {
        onView(withId(R.id.vehiclesPreviewButton)).perform(click());
        intended(hasComponent(VehicleInfoPanel.class.getName()));
    }
}
