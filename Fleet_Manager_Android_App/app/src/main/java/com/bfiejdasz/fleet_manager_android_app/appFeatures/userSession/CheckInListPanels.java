package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.ProblemsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.CheckInList;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.CheckInListDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ProblemNotSetError;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CheckInListPanels {
    private static CheckInListPanels instance;
    private QuestionHolder questionHolder;
    private CheckInPanel checkInPanel;
    private CheckInList checkInListDriver;

    private CheckInListPanels() {
        questionHolder = QuestionHolder.getInstance();

        questionHolder.addQuestion("Question 1", 0);
        questionHolder.addQuestion("Question 2", 0);
    }

    public static CheckInListPanels getInstance() {
        if (instance == null) {
            synchronized (CheckInListPanels.class) {
                if (instance == null) {
                    instance = new CheckInListPanels();
                }
            }
        }
        return instance;
    }

    public void start() {
        checkInPanel = new CheckInPanel();

        RideFactorySingleton.getInstance().getRideFactory().setContext(checkInPanel.getContext());
        try {
            checkInListDriver = RideFactorySingleton.getInstance().getRideFactory().checkInList();
        } catch (ContextNotSetException e) {
            throw new RuntimeException(e);
        }
        showNextQuestion();
    }

    public void showNextQuestion() {
        if (questionHolder.hasNextQuestion()) {
            Pair<String, Integer> questionPair = questionHolder.getNextQuestion();
            String question = questionPair.first;
            checkInPanel.setQuestion(question);
        } else {
            Log.e("Koniec pytan", "Koniec pytan");
        }
    }

    private void saveAnswerAndShowNextQuestion() {
        String answer = checkInPanel.getAnswer();

        if (!answer.equals("")) {
            int type = questionHolder.getType();

            ProblemsEntity problem = new ProblemsEntity();
            problem.setDescription(answer);
            problem.setProblemType((long) type);

            try {
                checkInListDriver.setProblem(problem);
            } catch (ProblemNotSetError e) {
                Log.e("EXCEPTION", "saveAnswerAndShowNextQuestion");
            }

            try {
                CompletableFuture<Boolean> futureResult = checkInListDriver.sendAnswerToServer();
                futureResult.thenAcceptAsync(success -> {
                    if (success) {
                        showNextQuestion();
                    } else {
                        Log.e("EXCEPTION", "saveAnswerAndShowNextQuestion");
                    }
                });
            } catch (ProblemNotSetError e) {
                Log.e("EXCEPTION", "saveAnswerAndShowNextQuestion");
            }
        } else {
            showNextQuestion();
        }
    }
}




