package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.PlanedRepairsEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IPlanedRepairsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("planedRepairs")
public class PlanedRepairsController {
    private final IPlanedRepairsRepository planedRepairsRepository;

    public PlanedRepairsController(IPlanedRepairsRepository planedRepairsRepository) {
        this.planedRepairsRepository = planedRepairsRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlanedRepairsEntity>> getAll() {
        List<PlanedRepairsEntity> output = planedRepairsRepository.findAll();

        if(output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }

    @GetMapping("")

    public ResponseEntity<List<PlanedRepairsEntity>> getPlanedRepairsForVehicle(@RequestParam("id") long id) {
        List<PlanedRepairsEntity> output = planedRepairsRepository.findAllByVehicleId(id);

        if(output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }


}
