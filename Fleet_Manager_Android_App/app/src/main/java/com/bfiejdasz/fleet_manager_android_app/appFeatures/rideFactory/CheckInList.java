package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.ProblemsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.ApplicationContextSingleton;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ProblemNotSetError;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CheckInList {
    protected ProblemsController problemsController;
    protected ProblemsEntity problem;
    protected ApplicationContextSingleton appContext;
    protected RideSession rideSession;

    public CheckInList() {
        this.appContext = ApplicationContextSingleton.getInstance();
        this.problemsController = new ProblemsController();
        this.rideSession = RideSession.getInstance();
    }

    public Context getContext() {
        return appContext.getAppContext();
    }

    public CompletableFuture<Boolean> sendAnswerToServer() throws ProblemNotSetError {
        if (problem != null) {
            List<ProblemsEntity> problemsList = new ArrayList<>();
            problemsList.add(problem);

            CompletableFuture<Boolean> futureResult = new CompletableFuture<>();
            problemsController.createProblem(problemsList, new Callback<List<ProblemsEntity>>() {
                @Override
                public void onResponse(Call<List<ProblemsEntity>> call, Response<List<ProblemsEntity>> response) {
                    if (response.isSuccessful()) {
                        futureResult.complete(true);
                    } else {
                        futureResult.complete(false);
                    }
                }

                @Override
                public void onFailure(Call<List<ProblemsEntity>> call, Throwable t) {
                    try {
                        ErrorHandler.handleException(t);
                    } catch (Exception e) {
                        ErrorHandler.logWithToastErrors(appContext.getAppContext(), e);
                    }
                    futureResult.complete(false);
                }
            });
            return futureResult;
        } else {
            throw new ProblemNotSetError("Problem wasn't set");
        }

    }

    public abstract void setProblem(ProblemsEntity problem) throws ProblemNotSetError;
}
