package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.EmployeesEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeesController {
    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping("")
    public List<EmployeesEntity> getAll() {
        return employeesRepository.getAll();
    }

    @GetMapping("/")
    public EmployeesEntity getEmployeeById(@RequestParam("id") long id) {
        return employeesRepository.getEmployeeById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<EmployeesEntity> employees) {
        return employeesRepository.save(employees);
    }

    @PatchMapping("/")
    public int update(@RequestParam("id") long id, @RequestBody EmployeesEntity updatedEmployee) {
        EmployeesEntity employee = employeesRepository.getEmployeeById(id);

        if (employee != null) {
            if (updatedEmployee.getName() != null) employee.setName(updatedEmployee.getName());
            if (updatedEmployee.getSurname() != null) employee.setSurname(updatedEmployee.getSurname());

            employeesRepository.update(employee);

            return 1;
        } else {
            //todo add code for exception
            return -1;
        }
    }

    @DeleteMapping("/")
    public int delete(@RequestParam("id") long id) {
        return employeesRepository.delete(id);
    }
}
