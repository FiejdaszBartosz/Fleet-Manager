package com.bfiejdasz.fleet_manager_rest_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "repairs", schema = "moloft", catalog = "postgres")
public class RepairsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_repairs")
    private long idRepairs;
    @Basic
    @Column(name = "vehicle")
    private Long vehicle;
    @Basic
    @Column(name = "problem")
    private Long problem;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicle", referencedColumnName = "id_vehicles", insertable=false, updatable=false)
    private VehiclesEntity vehiclesByVehicle;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "problem", referencedColumnName = "id_problems", insertable=false, updatable=false)
    private ProblemsEntity problemsByProblem;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairsEntity that = (RepairsEntity) o;

        if (idRepairs != that.idRepairs) return false;
        if (vehicle != null ? !vehicle.equals(that.vehicle) : that.vehicle != null) return false;
        if (problem != null ? !problem.equals(that.problem) : that.problem != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idRepairs ^ (idRepairs >>> 32));
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (problem != null ? problem.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
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
