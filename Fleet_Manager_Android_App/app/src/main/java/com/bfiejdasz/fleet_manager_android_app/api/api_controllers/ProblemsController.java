package com.bfiejdasz.fleet_manager_android_app.api.api_controllers;

import com.bfiejdasz.fleet_manager_android_app.api.ApiClient;
import com.bfiejdasz.fleet_manager_android_app.api.api_interfaces.IApiProblems;
import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProblemsController {
    private IApiProblems apiProblems;

    public ProblemsController() {
        apiProblems = ApiClient.getClient().create(IApiProblems.class);
    }

    public void setApiProblems(IApiProblems apiProblems) {
        this.apiProblems = apiProblems;
    }

    public void getProblems(Callback<List<ProblemsEntity>> callback) {
        Call<List<ProblemsEntity>> call = apiProblems.getProblems();
        BaseController<List<ProblemsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getProblemById(int id, Callback<ProblemsEntity> callback) {
        Call<ProblemsEntity> call = apiProblems.getProblemsById(id);
        BaseController<ProblemsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void getProblemsByRideId(int id, Callback<List<ProblemsEntity>> callback) {
        Call<List<ProblemsEntity>> call = apiProblems.getProblemsByRideId(id);
        BaseController<List<ProblemsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void deleteProblemById(int id, Callback<Void> callback) {
        Call<Void> call = apiProblems.deleteProblemById(id);
        BaseController<Void> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void createProblem(List<ProblemsEntity> problem, Callback<List<ProblemsEntity>> callback) {
        Call<List<ProblemsEntity>> call = apiProblems.createProblem(problem);
        BaseController<List<ProblemsEntity>> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }

    public void updateProblemById(int id, ProblemsEntity problem, final Callback<ProblemsEntity> callback) {
        Call<ProblemsEntity> call = apiProblems.updateProblemById(id, problem);
        BaseController<ProblemsEntity> baseController = new BaseController<>(call);
        baseController.execute(callback);
    }
}
