package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.sql.Date;
import java.util.Objects;

public class PlanedRepairsEntity {
    private long idPlanedRepairs;
    private Long vehicle;
    private Date date;
    private String description;
    private Integer km;
    private Date time;
    private VehiclesEntity vehiclesByVehicle;

    public long getIdPlanedRepairs() {
        return idPlanedRepairs;
    }

    public void setIdPlanedRepairs(long idPlanedRepairs) {
        this.idPlanedRepairs = idPlanedRepairs;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanedRepairsEntity that = (PlanedRepairsEntity) o;

        if (idPlanedRepairs != that.idPlanedRepairs) return false;
        if (!Objects.equals(vehicle, that.vehicle)) return false;
        if (!Objects.equals(date, that.date)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(km, that.km)) return false;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        int result = (int) (idPlanedRepairs ^ (idPlanedRepairs >>> 32));
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (km != null ? km.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public VehiclesEntity getVehiclesByVehicle() {
        return vehiclesByVehicle;
    }

    public void setVehiclesByVehicle(VehiclesEntity vehiclesByVehicle) {
        this.vehiclesByVehicle = vehiclesByVehicle;
    }
}
