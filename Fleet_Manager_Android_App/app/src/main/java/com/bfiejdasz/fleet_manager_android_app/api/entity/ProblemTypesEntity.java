package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.util.Objects;

public class ProblemTypesEntity {
    private long idProblemTypes;
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
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (idProblemTypes ^ (idProblemTypes >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
