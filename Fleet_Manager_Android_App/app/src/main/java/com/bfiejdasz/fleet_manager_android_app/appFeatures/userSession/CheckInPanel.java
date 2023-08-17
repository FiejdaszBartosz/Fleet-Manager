package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.CheckInList;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ProblemNotSetError;

import java.util.concurrent.CompletableFuture;

public class CheckInPanel extends AppCompatActivity {
    private TextView questionTextView;
    private EditText answerEditText;
    private ImageButton nextButton;
    private ApplicationContextSingleton appContext;
    private QuestionHolder questionHolder;
    private Pair<String, Integer> question;
    private CheckInList checkInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin_panel);

        appContext = ApplicationContextSingleton.getInstance();
        Context context = this;
        appContext.setAppContext(context);

        questionHolder = QuestionHolder.getInstance();

        try {
            checkInList = RideFactorySingleton.getInstance().getRideFactory().checkInList();
        } catch (ContextNotSetException e) {
            throw new RuntimeException(e);
        }

        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        nextButton = findViewById(R.id.nextButton);

        question = questionHolder.getNextQuestion();
        questionTextView.setText(question.first);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerEditText.getText().toString();
                if (!answer.isEmpty()) {
                    ProblemsEntity problem = new ProblemsEntity();
                    problem.setDescription(answer);
                    problem.setProblemType((long) question.second);
                    problem.setRideId(RideSession.getInstance().getRide().getRideId());

                    try {
                        checkInList.setProblem(problem);
                        CompletableFuture<Boolean> futureResult = checkInList.sendAnswerToServer();
                        futureResult.thenAcceptAsync(success -> {
                            if (success) {
                                if (questionHolder.hasNextQuestion()) {
                                    Intent intent = new Intent(context, CheckInPanel.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(context, RidePanel.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Log.e("EXCEPTION", "CheckInPanel");
                            }
                        });
                    } catch (ProblemNotSetError e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    if (questionHolder.hasNextQuestion()) {
                        Intent intent = new Intent(context, CheckInPanel.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(context, RidePanel.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    public void setQuestion(String question) {
        questionTextView.setText(question);
    }

    public String getAnswer() {
        return answerEditText.getText().toString();
    }
}
