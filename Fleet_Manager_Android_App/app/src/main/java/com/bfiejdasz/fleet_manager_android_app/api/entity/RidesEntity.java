package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

public class RidesEntity {
    private long idRides;
    private Long rideId;
    private Long vehicle;
    private String startTime;
    private String stopTime;
    private Integer startKm;
    private Integer stopKm;

    private Integer startFuel;
    private Integer stopFuel;
    private VehiclesEntity vehiclesByVehicle;
    private RidesEmployeesEntity ridesEmployeesByRideId;
    private Collection<ProblemsEntity> problemsByRideid;
    private Collection<PositionsEntity> positionsByRideid;

    public long getIdRides() {
        return idRides;
    }

    public void setIdRides(long idRides) {
        this.idRides = idRides;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getStartKm() {
        return startKm;
    }

    public void setStartKm(Integer startKm) {
        this.startKm = startKm;
    }

    public Integer getStopKm() {
        return stopKm;
    }

    public void setStopKm(Integer stopKm) {
        this.stopKm = stopKm;
    }

    public Integer getStartFuel() {
        return startFuel;
    }

    public void setStartFuel(Integer startFuel) {
        this.startFuel = startFuel;
    }

    public Integer getStopFuel() {
        return stopFuel;
    }

    public void setStopFuel(Integer stopFuel) {
        this.stopFuel = stopFuel;
    }

    public void createRideId() {
        this.rideId = (long) hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RidesEntity that = (RidesEntity) o;

        if (idRides != that.idRides) return false;
        if (rideId != null ? !rideId.equals(that.rideId) : that.rideId != null) return false;
        if (vehicle != null ? !vehicle.equals(that.vehicle) : that.vehicle != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (stopTime != null ? !stopTime.equals(that.stopTime) : that.stopTime != null) return false;
        if (startKm != null ? !startKm.equals(that.startKm) : that.startKm != null) return false;
        if (stopKm != null ? !stopKm.equals(that.stopKm) : that.stopKm != null) return false;
        if (startFuel != null ? !startFuel.equals(that.startFuel) : that.startFuel != null) return false;
        if (stopFuel != null ? !stopFuel.equals(that.stopFuel) : that.stopFuel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idRides ^ (idRides >>> 32));
        result = 31 * result + (rideId != null ? rideId.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (stopTime != null ? stopTime.hashCode() : 0);
        result = 31 * result + (startKm != null ? startKm.hashCode() : 0);
        result = 31 * result + (stopKm != null ? stopKm.hashCode() : 0);
        result = 31 * result + (startFuel != null ? startFuel.hashCode() : 0);
        result = 31 * result + (stopFuel != null ? stopFuel.hashCode() : 0);
        return Math.abs(result);
    }

    public VehiclesEntity getVehiclesByVehicle() {
        return vehiclesByVehicle;
    }

    public void setVehiclesByVehicle(VehiclesEntity vehiclesByVehicle) {
        this.vehiclesByVehicle = vehiclesByVehicle;
    }

    public RidesEmployeesEntity getRidesEmployeesByRideId() {
        return ridesEmployeesByRideId;
    }

    public void setRidesEmployeesByRideId(RidesEmployeesEntity ridesEmployeesByRideId) {
        this.ridesEmployeesByRideId = ridesEmployeesByRideId;
    }

    public Collection<ProblemsEntity> getProblemsByRideid() {
        return problemsByRideid;
    }

    public void setProblemsByRideid(Collection<ProblemsEntity> problemsByRideid) {
        this.problemsByRideid = problemsByRideid;
    }

    public Collection<PositionsEntity> getPositionsByRideid() {
        return positionsByRideid;
    }

    public void setPositionsByRideid(Collection<PositionsEntity> positionsByRideid) {
        this.positionsByRideid = positionsByRideid;
    }
}
