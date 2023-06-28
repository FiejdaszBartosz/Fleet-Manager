package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.ProblemsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.CheckInList;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.RideFactorySingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver.CheckInListDriver;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ContextNotSetException;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ProblemNotSetError;

public class CheckInPanel extends AppCompatActivity {
    private TextView questionTextView;
    private EditText answerEditText;
    private Button nextButton;
    private Context context;
    private QuestionHolder questionHolder;
    private Pair<String, Integer> question;
    private CheckInList checkInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin_panel);
        this.context = this;
        questionHolder = QuestionHolder.getInstance();

        RideFactorySingleton.getInstance().getRideFactory().setContext(this);

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
                if (!answer.equals("")) {
                    ProblemsEntity problem = new ProblemsEntity();

                    problem.setDescription(answer);
                    problem.setProblemType((long) question.second);

                    try {
                        checkInList.setProblem(problem);
                        checkInList.sendAnswerToServer();
                    } catch (ProblemNotSetError e) {
                        throw new RuntimeException(e);
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

    public Context getContext() {
        return context;
    }
}
