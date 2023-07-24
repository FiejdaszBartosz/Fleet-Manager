package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.PlanedRepairsEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IPlanedRepairsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<PlanedRepairsEntity> planedRepairs) {
        try {
            planedRepairsRepository.saveAll(planedRepairs);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody PlanedRepairsEntity planedRepairs) {
        Optional<PlanedRepairsEntity> optional = planedRepairsRepository.findById(id);

        if (optional.isPresent()) {
            PlanedRepairsEntity temp = optional.get();

            if(planedRepairs.getVehicle() != null) temp.setVehicle(planedRepairs.getVehicle());
            if(planedRepairs.getDate() != null) temp.setDate(planedRepairs.getDate());
            if(planedRepairs.getDescription() != null) temp.setDescription(planedRepairs.getDescription());
            if(planedRepairs.getKm() != null) temp.setKm(planedRepairs.getKm());
            if(planedRepairs.getTime() != null) temp.setTime(planedRepairs.getTime());

            planedRepairsRepository.save(temp);

            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> delete(@RequestParam("id") long id) {
        Optional<PlanedRepairsEntity> optional = planedRepairsRepository.findById(id);

        if (optional.isPresent()) {
            planedRepairsRepository.delete(optional.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planed repair not found");
        }
    }


}
