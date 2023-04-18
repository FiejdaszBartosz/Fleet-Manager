package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.RidesEmployeesEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IRidesEmployeesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ridesEmployees")
public class RidesEmployeesController {
    private final IRidesEmployeesRepository ridesEmployeesRepository;

    public RidesEmployeesController(IRidesEmployeesRepository ridesEmployeesRepository) {
        this.ridesEmployeesRepository = ridesEmployeesRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RidesEmployeesEntity>> getAllVehicles() {
        List<RidesEmployeesEntity> ridesEmployees = ridesEmployeesRepository.findAll();
        return new ResponseEntity<>(ridesEmployees, HttpStatus.OK);
    }

    @GetMapping("/getByEmployee")
    public ResponseEntity<List<RidesEmployeesEntity>> getRidesEmployeesByEmployee(@RequestParam("id") long id) {
        List<RidesEmployeesEntity> ridesEmployees = ridesEmployeesRepository.findAllByEmployeeId(id);

        if (!ridesEmployees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(ridesEmployees);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/getByRide")
    public ResponseEntity<List<RidesEmployeesEntity>> getRidesEmployeesByRide(@RequestParam("id") long id) {
        List<RidesEmployeesEntity> ridesEmployees = ridesEmployeesRepository.findAllByRideid(id);

        if (!ridesEmployees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(ridesEmployees);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<RidesEmployeesEntity> ridesEmployees) {
        try {
            ridesEmployeesRepository.saveAll(ridesEmployees);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
