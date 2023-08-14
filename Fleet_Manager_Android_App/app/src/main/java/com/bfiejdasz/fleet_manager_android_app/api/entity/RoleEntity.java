package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.util.Collection;
import java.util.Objects;

public class RoleEntity {
    private long idRoles;
    private String roleName;
    private Collection<EmployeesEntity> employeesByIdRoles;

    public long getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(long idRoles) {
        this.idRoles = idRoles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (idRoles != that.idRoles) return false;
        return Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        int result = (int) (idRoles ^ (idRoles >>> 32));
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    public Collection<EmployeesEntity> getEmployeesByIdRoles() {
        return employeesByIdRoles;
    }

    public void setEmployeesByIdRoles(Collection<EmployeesEntity> employeesByIdRoles) {
        this.employeesByIdRoles = employeesByIdRoles;
    }
}
