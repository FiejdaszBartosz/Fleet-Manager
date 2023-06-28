package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.dirver;

import com.bfiejdasz.fleet_manager_android_app.api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.CheckInList;
import com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors.ProblemNotSetError;

public class CheckInListDriver extends CheckInList {
    public CheckInListDriver() {
        super();
    }

    public void setProblem(ProblemsEntity problem) throws ProblemNotSetError {
        if (problem.getProblemType() == null || problem.getDescription() == null)
            throw new ProblemNotSetError("Missing parameters in problem");

        problem.setIsSolved((short) 0);
        problem.setRideId(rideSession.getRide().getRideId());
        this.problem = problem;
    }
}
