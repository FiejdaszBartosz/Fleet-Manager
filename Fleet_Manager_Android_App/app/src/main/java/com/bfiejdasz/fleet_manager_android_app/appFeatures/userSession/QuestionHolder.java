package com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class QuestionHolder {
    private static QuestionHolder instance;
    private List<Pair<String, Integer>> questions;
    private int currentIndex;

    private QuestionHolder() {
        questions = new ArrayList<>();
        currentIndex = 0;
        setQuestions();
    }

    public static QuestionHolder getInstance() {
        if (instance == null) {
            synchronized (QuestionHolder.class) {
                if (instance == null) {
                    instance = new QuestionHolder();
                }
            }
        }
        return instance;
    }

    public void addQuestion(String question, int type) {
        Pair<String, Integer> questionPair = new Pair<>(question, type);
        questions.add(questionPair);
    }

    public boolean hasNextQuestion() {
        return currentIndex < questions.size();
    }

    public Pair<String, Integer> getNextQuestion() {
        if (currentIndex < questions.size()) {
            Pair<String, Integer> questionPair = questions.get(currentIndex);
            currentIndex++;
            return questionPair;
        }
        return null;
    }

    public int getType() {
        return questions.get(currentIndex).second;
    }

    private void setQuestions() {
        addQuestion("Czy występują jakieś problemy z lakierem?", 1);
        addQuestion("Czy światła działają poprawnie?", 1);
        addQuestion("Czy poziom płynów jest odpowiedni?", 1);
        addQuestion("Czy wnętrze pojazdu jest w porządku?", 1);
    }
}


