package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory;

import android.content.Context;

import com.bfiejdasz.fleet_manager_android_app.api.api_controllers.ProblemsController;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ProblemNotSetError;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.userSession.RideSession;
import com.bfiejdasz.fleet_manager_android_app.exceptions.ErrorHandler;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CheckInList {
    protected ProblemsController problemsController;
    protected ProblemsEntity problem;
    protected Context context;
    protected RideSession rideSession;

    public CheckInList() {
        this.problemsController = new ProblemsController();
        this.rideSession = RideSession.getInstance();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CompletableFuture<Boolean> sendAnswerToServer() throws ProblemNotSetError {
        if (problem != null) {
            CompletableFuture<Boolean> futureResult = new CompletableFuture<>();
            problemsController.createProblem(problem, new Callback<ProblemsEntity>() {
                @Override
                public void onResponse(Call<ProblemsEntity> call, Response<ProblemsEntity> response) {
                    if (response.isSuccessful()) {
                        futureResult.complete(true);
                    } else {
                        futureResult.complete(false);
                    }
                }

                @Override
                public void onFailure(Call<ProblemsEntity> call, Throwable t) {
                    try {
                        ErrorHandler.handleException(t);
                    } catch (Exception e) {
                        ErrorHandler.logWithToastErrors(context, e);
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
