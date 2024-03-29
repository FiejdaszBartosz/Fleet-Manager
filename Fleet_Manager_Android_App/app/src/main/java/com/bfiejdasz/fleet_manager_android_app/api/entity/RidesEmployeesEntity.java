package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.util.Objects;

public class RidesEmployeesEntity {
    private long idRidesEmployees;
    private Long rideId;
    private Long idEmployee;
    private EmployeesEntity employeesByIdEmployee;

    public long getIdRidesEmployees() {
        return idRidesEmployees;
    }

    public void setIdRidesEmployees(long idRidesEmployees) {
        this.idRidesEmployees = idRidesEmployees;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RidesEmployeesEntity that = (RidesEmployeesEntity) o;

        if (idRidesEmployees != that.idRidesEmployees) return false;
        if (!Objects.equals(rideId, that.rideId)) return false;
        return Objects.equals(idEmployee, that.idEmployee);
    }

    @Override
    public int hashCode() {
        int result = (int) (idRidesEmployees ^ (idRidesEmployees >>> 32));
        result = 31 * result + (rideId != null ? rideId.hashCode() : 0);
        result = 31 * result + (idEmployee != null ? idEmployee.hashCode() : 0);
        return result;
    }

    public EmployeesEntity getEmployeesByIdEmployee() {
        return employeesByIdEmployee;
    }

    public void setEmployeesByIdEmployee(EmployeesEntity employeesByIdEmployee) {
        this.employeesByIdEmployee = employeesByIdEmployee;
    }
}
