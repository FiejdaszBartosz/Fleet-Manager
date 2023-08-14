package com.bfiejdasz.fleet_manager_android_app.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class EmployeesEntity {
    @SerializedName("idEmployees")
    private long idEmployees;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private Long role;

    public long getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(long idEmployees) {
        this.idEmployees = idEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesEntity that = (EmployeesEntity) o;

        if (idEmployees != that.idEmployees) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(surname, that.surname)) return false;
        if (!Objects.equals(login, that.login)) return false;
        if (!Objects.equals(password, that.password)) return false;
        return Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        int result = (int) (idEmployees ^ (idEmployees >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
