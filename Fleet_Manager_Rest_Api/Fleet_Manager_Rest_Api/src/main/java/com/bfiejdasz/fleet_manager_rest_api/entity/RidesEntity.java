package com.bfiejdasz.fleet_manager_rest_api.entity;

import com.bfiejdasz.fleet_manager_rest_api.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "rides", schema = "moloft", catalog = "postgres")
public class RidesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rides")
    private long idRides;
    @Basic
    @Column(name = "rideid", unique = true)
    private Long rideId;
    @Basic
    @Column(name = "vehicle")
    private Long vehicle;
    @Basic
    @Column(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime startTime;
    @Basic
    @Column(name = "stop_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime stopTime;
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
    @JoinColumn(name = "vehicle", referencedColumnName = "id_vehicles", insertable=false, updatable=false)
    @JsonIgnore
    private VehiclesEntity vehiclesByVehicle;

    @OneToMany(mappedBy = "ridesEmployeesByRideid")
    @JsonIgnore
    private Collection<RidesEmployeesEntity> ridesEmployeesByRideid;

    @OneToMany(mappedBy = "problemsByRideid")
    @JsonIgnore
    private Collection<ProblemsEntity> problemsByRideid;

    @OneToMany(mappedBy = "positionsByRideid")
    @JsonIgnore
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getStartTime() {
        return startTime;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getStopTime() {
        return stopTime;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public void setStopTime(LocalDateTime stopTime) {
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

    public VehiclesEntity getVehiclesByVehicle() {
        return vehiclesByVehicle;
    }

    public void setVehiclesByVehicle(VehiclesEntity vehiclesByVehicle) {
        this.vehiclesByVehicle = vehiclesByVehicle;
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

    public Collection<RidesEmployeesEntity> getRidesEmployeesByRideId() {
        return ridesEmployeesByRideid;
    }

    public void setRidesEmployeesByRideId(Collection<RidesEmployeesEntity> ridesEmployeesByRideid) {
        this.ridesEmployeesByRideid = ridesEmployeesByRideid;
    }
}
