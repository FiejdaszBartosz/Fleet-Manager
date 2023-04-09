package com.bfiejdasz.fleet_manager_rest_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rides_employees", schema = "moloft", catalog = "postgres")
public class RidesEmployeesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rides_employees")
    private long idRidesEmployees;
    @Basic
    @Column(name = "rideid")
    private Long rideId;
    @Basic
    @Column(name = "id_employee")
    private Long idEmployee;
    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employees", insertable=false, updatable=false)
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
        if (rideId != null ? !rideId.equals(that.rideId) : that.rideId != null) return false;
        if (idEmployee != null ? !idEmployee.equals(that.idEmployee) : that.idEmployee != null) return false;

        return true;
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
