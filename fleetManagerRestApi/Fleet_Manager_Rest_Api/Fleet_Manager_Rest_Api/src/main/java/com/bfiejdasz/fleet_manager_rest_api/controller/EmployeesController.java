package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeesController {
    private final EmployeesRepository employeesRepository;

    public EmployeesController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeesEntity>> getAll() {
        List<EmployeesEntity> employees = employeesRepository.getAll();
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(employees);
        }
    }

    @GetMapping("/")
    public ResponseEntity<EmployeesEntity> getEmployeeById(@RequestParam("id") long id) {
        EmployeesEntity employee = employeesRepository.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<EmployeesEntity> employees) {
        try {
            employeesRepository.save(employees);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/")
    public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody EmployeesEntity updatedEmployee) {
        EmployeesEntity employee = employeesRepository.getEmployeeById(id);

        if (employee != null) {
            if (updatedEmployee.getName() != null) employee.setName(updatedEmployee.getName());
            if (updatedEmployee.getSurname() != null) employee.setSurname(updatedEmployee.getSurname());

            employeesRepository.update(employee);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> delete(@RequestParam("id") long id) {
        int result = employeesRepository.delete(id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/login")
    public boolean checkCredentials(@RequestParam("login") String login, @RequestParam("password") String password) {
        return employeesRepository.checkCredentials(login, password);
    }

}
