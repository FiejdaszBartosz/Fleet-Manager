package com.bfiejdasz.fleet_manager_rest_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "problem_types", schema = "moloft", catalog = "postgres")
public class ProblemTypesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_problem_types")
    private long idProblemTypes;
    @Basic
    @Column(name = "name")
    private String name;

    public long getIdProblemTypes() {
        return idProblemTypes;
    }

    public void setIdProblemTypes(long idProblemTypes) {
        this.idProblemTypes = idProblemTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemTypesEntity that = (ProblemTypesEntity) o;

        if (idProblemTypes != that.idProblemTypes) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idProblemTypes ^ (idProblemTypes >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
