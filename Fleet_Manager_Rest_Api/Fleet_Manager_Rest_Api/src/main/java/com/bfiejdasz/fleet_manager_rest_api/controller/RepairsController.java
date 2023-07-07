package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.RepairsEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IRepairsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("repairs")
public class RepairsController {
    private final IRepairsRepository repairsRepository;

    public RepairsController(IRepairsRepository repairsRepository) {
        this.repairsRepository = repairsRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RepairsEntity>> getAll() {
        List<RepairsEntity> output = repairsRepository.findAll();

        if (output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }

    @GetMapping("")
    public ResponseEntity<RepairsEntity> getRepairsById(@RequestParam("id") long id) {
        Optional<RepairsEntity> optional = repairsRepository.findById(id);

        if(optional.isPresent()) {
            RepairsEntity output = optional.get();
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/vehicleid")
    public ResponseEntity<List<RepairsEntity>> getRepairsForVehicle(@RequestParam("id") long id) {
        List<RepairsEntity> output = repairsRepository.findAllByVehicleId(id);

        if (output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<RepairsEntity> repairs) {
        try {
            repairsRepository.saveAll(repairs);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody RepairsEntity repairs) {
        Optional<RepairsEntity> optional = repairsRepository.findById(id);

        if (optional.isPresent()) {
            RepairsEntity temp = optional.get();

            if (repairs.getVehicle() != null) temp.setVehicle(repairs.getVehicle());
            if (repairs.getProblem() != null) temp.setProblem(repairs.getProblem());
            if (repairs.getDescription() != null) temp.setDescription(repairs.getDescription());
            if (repairs.getComplete() != null) temp.setComplete(repairs.getComplete());

            repairsRepository.save(temp);

            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> delete(@RequestParam("id") long id) {
        Optional<RepairsEntity> optional = repairsRepository.findById(id);

        if (optional.isPresent()) {
            repairsRepository.delete(optional.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Repair not found");
        }
    }
}
