package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.sql.Timestamp;

public class RepairsEntity {
    private long idRepairs;
    private Long vehicle;
    private Long problem;
    private String description;
    private VehiclesEntity vehiclesByVehicle;
    private ProblemsEntity problemsByProblem;
    private Short complete;
    private String date;

    public long getIdRepairs() {
        return idRepairs;
    }

    public void setIdRepairs(long idRepairs) {
        this.idRepairs = idRepairs;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public Long getProblem() {
        return problem;
    }

    public void setProblem(Long problem) {
        this.problem = problem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getComplete() {
        return complete;
    }

    public void setComplete(Short complete) {
        this.complete = complete;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairsEntity that = (RepairsEntity) o;

        if (idRepairs != that.idRepairs) return false;
        if (vehicle != null ? !vehicle.equals(that.vehicle) : that.vehicle != null) return false;
        if (problem != null ? !problem.equals(that.problem) : that.problem != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (complete != null ? !complete.equals(that.complete) : that.complete != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idRepairs ^ (idRepairs >>> 32));
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (problem != null ? problem.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);

        return result;
    }

    public VehiclesEntity getVehiclesByVehicle() {
        return vehiclesByVehicle;
    }

    public void setVehiclesByVehicle(VehiclesEntity vehiclesByVehicle) {
        this.vehiclesByVehicle = vehiclesByVehicle;
    }

    public ProblemsEntity getProblemsByProblem() {
        return problemsByProblem;
    }

    public void setProblemsByProblem(ProblemsEntity problemsByProblem) {
        this.problemsByProblem = problemsByProblem;
    }
}
