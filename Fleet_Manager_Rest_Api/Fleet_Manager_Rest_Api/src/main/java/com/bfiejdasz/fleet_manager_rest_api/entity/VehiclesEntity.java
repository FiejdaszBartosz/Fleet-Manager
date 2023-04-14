package com.bfiejdasz.fleet_manager_rest_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "vehicles", schema = "moloft", catalog = "postgres")
public class VehiclesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_vehicles")
    private long idVehicles;
    @Basic
    @Column(name = "manufacture")
    private String manufacture;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "vin")
    private String vin;
    @Basic
    @Column(name = "license_plate")
    private String licensePlate;
    @Basic
    @Column(name = "mileage")
    private Integer mileage;
    @Basic
    @Column(name = "insurance")
    private String insurance;
    @Basic
    @Column(name = "in_use")
    private Short inUse;
    @OneToMany(mappedBy = "vehiclesByVehicle")
    @JsonIgnore
    private Collection<PlanedRepairsEntity> planedRepairsByIdVehicles;
    @OneToMany(mappedBy = "vehiclesByVehicle")
    @JsonIgnore
    private Collection<RepairsEntity> repairsByIdVehicles;
    @OneToMany(mappedBy = "vehiclesByVehicle")
    @JsonIgnore
    private Collection<RidesEntity> ridesByIdVehicles;

    public long getIdVehicles() {
        return idVehicles;
    }

    public void setIdVehicles(long idVehicles) {
        this.idVehicles = idVehicles;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Short getInUse() {
        return inUse;
    }

    public void setInUse(Short inUse) {
        this.inUse = inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehiclesEntity that = (VehiclesEntity) o;

        if (idVehicles != that.idVehicles) return false;
        if (manufacture != null ? !manufacture.equals(that.manufacture) : that.manufacture != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;
        if (licensePlate != null ? !licensePlate.equals(that.licensePlate) : that.licensePlate != null) return false;
        if (mileage != null ? !mileage.equals(that.mileage) : that.mileage != null) return false;
        if (insurance != null ? !insurance.equals(that.insurance) : that.insurance != null) return false;
        if (inUse != null ? !inUse.equals(that.inUse) : that.inUse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idVehicles ^ (idVehicles >>> 32));
        result = 31 * result + (manufacture != null ? manufacture.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (licensePlate != null ? licensePlate.hashCode() : 0);
        result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
        result = 31 * result + (insurance != null ? insurance.hashCode() : 0);
        result = 31 * result + (inUse != null ? inUse.hashCode() : 0);
        return result;
    }

    public Collection<PlanedRepairsEntity> getPlanedRepairsByIdVehicles() {
        return planedRepairsByIdVehicles;
    }

    public void setPlanedRepairsByIdVehicles(Collection<PlanedRepairsEntity> planedRepairsByIdVehicles) {
        this.planedRepairsByIdVehicles = planedRepairsByIdVehicles;
    }

    public Collection<RepairsEntity> getRepairsByIdVehicles() {
        return repairsByIdVehicles;
    }

    public void setRepairsByIdVehicles(Collection<RepairsEntity> repairsByIdVehicles) {
        this.repairsByIdVehicles = repairsByIdVehicles;
    }

    public Collection<RidesEntity> getRidesByIdVehicles() {
        return ridesByIdVehicles;
    }

    public void setRidesByIdVehicles(Collection<RidesEntity> ridesByIdVehicles) {
        this.ridesByIdVehicles = ridesByIdVehicles;
    }
}
