package com.bfiejdasz.fleet_manager_rest_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "problems", schema = "moloft", catalog = "postgres")
public class ProblemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_problems")
    private long idProblems;
    @Basic
    @Column(name = "rideid")
    private Long rideId;
    @Basic
    @Column(name = "type")
    private Long type;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "is_solved")
    private Short isSolved;
    @ManyToOne
    @JsonProperty("type")
    @JsonIgnore
    @JoinColumn(name = "type", referencedColumnName = "id_problem_types", insertable=false, updatable=false)
    private ProblemTypesEntity problemTypesByProblemType;
    @OneToMany(mappedBy = "problemsByProblem")
    @JsonIgnore
    private Collection<RepairsEntity> repairsByIdProblems;

    public long getIdProblems() {
        return idProblems;
    }

    public void setIdProblems(long idProblems) {
        this.idProblems = idProblems;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getProblemType() {
        return type;
    }

    public void setProblemType(Long type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Short isSolved) {
        this.isSolved = isSolved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemsEntity that = (ProblemsEntity) o;

        if (idProblems != that.idProblems) return false;
        if (rideId != null ? !rideId.equals(that.rideId) : that.rideId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (isSolved != null ? !isSolved.equals(that.isSolved) : that.isSolved != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idProblems ^ (idProblems >>> 32));
        result = 31 * result + (rideId != null ? rideId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isSolved != null ? isSolved.hashCode() : 0);
        return result;
    }

    public ProblemTypesEntity getProblemTypesByProblemType() {
        return problemTypesByProblemType;
    }

    public void setProblemTypesByProblemType(ProblemTypesEntity problemTypesByProblemType) {
        this.problemTypesByProblemType = problemTypesByProblemType;
    }

    public Collection<RepairsEntity> getRepairsByIdProblems() {
        return repairsByIdProblems;
    }

    public void setRepairsByIdProblems(Collection<RepairsEntity> repairsByIdProblems) {
        this.repairsByIdProblems = repairsByIdProblems;
    }
}
