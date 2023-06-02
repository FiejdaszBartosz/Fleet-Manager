package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.EmployeesEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<EmployeesEntity> getAll() {
        return jdbcTemplate.query(
                "Select id_employees, name, surname, login, password, role from moloft.employees",
                BeanPropertyRowMapper.newInstance(EmployeesEntity.class));
    }

    public EmployeesEntity getEmployeeById(long id) {
        return jdbcTemplate.queryForObject(
                "Select id_employees, name, surname, login, password, role " +
                        "from moloft.employees " +
                        "where id_employees = ?",
                BeanPropertyRowMapper.newInstance(EmployeesEntity.class), id);
    }

    public int save(List<EmployeesEntity> employees) {
        employees.forEach(i ->
                jdbcTemplate.update(
                        "insert into " +
                                "moloft.employees(name, surname, login, password)" +
                                "values (?, ?, ?, ?)",
                        i.getName(), i.getSurname(), i.getLogin(), i.getPassword())
        );

        return 1;
    }

    public int update(EmployeesEntity employee) {
        return jdbcTemplate.update(
                "update moloft.employees " +
                        "set name=?, surname=? where id_employees=?",
                employee.getName(), employee.getSurname(), employee.getIdEmployees()
        );
    }

    public int delete(long id) {
        return jdbcTemplate.update(
                "delete from moloft.employees " +
                        "where id_employees=?",
                id
        );
    }

    public EmployeesEntity checkCredentials(String login, String password) {
        String query = "SELECT * FROM moloft.employees WHERE login = ?";
        try {
            EmployeesEntity employee = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(EmployeesEntity.class), login);
            String storedHash = employee.getPassword();
            boolean match = BCrypt.checkpw(password, storedHash);
            if (match) {
                return employee;
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
