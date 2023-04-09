package com.bfiejdasz.fleet_manager_rest_api.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "rides", schema = "moloft", catalog = "postgres")
public class RidesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rides")
    private long idRides;
    @Basic
    @Column(name = "rideid")
    private Long rideId;
    @Basic
    @Column(name = "vehicle")
    private Long vehicle;
    @Basic
    @Column(name = "start_time")
    private Timestamp startTime;
    @Basic
    @Column(name = "stop_time")
    private Timestamp stopTime;
    @Basic
    @Column(name = "start_km")
    private Integer startKm;
    @Basic
    @Column(name = "stop_km")
    private Integer stopKm;
    @Basic
    @Column(name = "start_fuel")
    private Integer startFuel;
    @Basic
    @Column(name = "stop_fuel")
    private Integer stopFuel;
    @ManyToOne
    @JoinColumn(name = "rideid", referencedColumnName = "rideid", insertable=false, updatable=false)
    private PositionsEntity positionsByRideId;
    @ManyToOne
    @JoinColumn(name = "rideid", referencedColumnName = "rideid", insertable=false, updatable=false)
    private ProblemsEntity problemsByRideId;
    @ManyToOne
    @JoinColumn(name = "vehicle", referencedColumnName = "id_vehicles", insertable=false, updatable=false)
    private VehiclesEntity vehiclesByVehicle;
    @ManyToOne
    @JoinColumn(name = "rideid", referencedColumnName = "rideid", insertable=false, updatable=false)
    private RidesEmployeesEntity ridesEmployeesByRideId;

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getStopTime() {
        return stopTime;
    }

    public void setStopTime(Timestamp stopTime) {
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
        return result;
    }

    public PositionsEntity getPositionsByRideId() {
        return positionsByRideId;
    }

    public void setPositionsByRideId(PositionsEntity positionsByRideId) {
        this.positionsByRideId = positionsByRideId;
    }

    public ProblemsEntity getProblemsByRideId() {
        return problemsByRideId;
    }

    public void setProblemsByRideId(ProblemsEntity problemsByRideId) {
        this.problemsByRideId = problemsByRideId;
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
}
