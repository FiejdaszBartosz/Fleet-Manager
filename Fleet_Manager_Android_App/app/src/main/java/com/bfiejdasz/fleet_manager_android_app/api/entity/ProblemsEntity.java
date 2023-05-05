package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.util.Collection;

public class ProblemsEntity {
    private long idProblems;
    private Long rideId;
    private Long type;
    private String description;
    private Short isSolved;
    private ProblemTypesEntity problemTypesByProblemType;
    private Collection<RepairsEntity> repairsByIdProblems;

    private RidesEntity problemsByRideid;

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
