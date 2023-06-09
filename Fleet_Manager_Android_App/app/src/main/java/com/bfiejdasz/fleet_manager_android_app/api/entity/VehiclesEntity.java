package com.bfiejdasz.fleet_manager_android_app.api.entity;

import com.bfiejdasz.fleet_manager_android_app.api.ITableItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VehiclesEntity implements ITableItem {
    private long idVehicles;
    private String manufacture;
    private String model;
    private Integer year;
    private String vin;
    private String licensePlate;
    private Integer mileage;
    private String insurance;
    private Short inUse;
    private Collection<PlanedRepairsEntity> planedRepairsByIdVehicles;
    private Collection<RepairsEntity> repairsByIdVehicles;
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

    @Override
    public List<String> getColumns() {
        List<String> columns = new ArrayList<>();
        columns.add("ID");
        columns.add("Manufacturer");
        columns.add("Model");
        columns.add("Year");
        columns.add("VIN");
        columns.add("License Plate");
        columns.add("Mileage");
        columns.add("Insurance");
        columns.add("In Use");
        return columns;
    }

    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(getIdVehicles()));
        values.add(getValueOrNotSet(getManufacture()));
        values.add(getValueOrNotSet(getModel()));
        values.add(getValueOrNotSet(String.valueOf(getYear())));
        values.add(getValueOrNotSet(getVin()));
        values.add(getValueOrNotSet(getLicensePlate()));
        values.add(getValueOrNotSet(String.valueOf(getMileage())));
        values.add(getValueOrNotSet(getInsurance()));
        values.add(getValueOrNotSet(String.valueOf(getInUse())));
        return values;
    }

    private String getValueOrNotSet(String value) {
        return value != null && !value.isEmpty() ? value : "Not Set";
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
