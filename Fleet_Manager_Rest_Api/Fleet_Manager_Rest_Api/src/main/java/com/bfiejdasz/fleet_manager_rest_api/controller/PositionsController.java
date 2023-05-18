package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IPositionsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("positions")
public class PositionsController {
    private final IPositionsRepository positionsRepository;

    public PositionsController(IPositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PositionsEntity>> getAll() {
        List<PositionsEntity> output = positionsRepository.findAll();

        if(output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }

    @GetMapping("")
    public ResponseEntity<PositionsEntity> getPositionById(@RequestParam("id") long id) {
        Optional<PositionsEntity> optional = positionsRepository.findById(id);

        if(optional.isPresent()) {
            PositionsEntity output = optional.get();
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/rideid")
    public ResponseEntity<List<PositionsEntity>> getPositionsById(@RequestParam("id") long id) {
        List<PositionsEntity> output = positionsRepository.findAllByRideid(id);

        if(output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<PositionsEntity> positionsEntities) {
        try {
            PositionsEntity positionsEntity = positionsEntities.get(0);
            positionsRepository.saveAll(positionsEntities);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody PositionsEntity positionsEntity) {
        Optional<PositionsEntity> optional = positionsRepository.findById(id);

        if (optional.isPresent()) {
            PositionsEntity temp = optional.get();

            if(positionsEntity.getRideid() != null) temp.setRideid(positionsEntity.getRideid());
            if(positionsEntity.getxCord() != null) temp.setxCord(positionsEntity.getxCord());
            if(positionsEntity.getyCord() != null) temp.setyCord(positionsEntity.getyCord());
            if(positionsEntity.getTime() != null) temp.setTime(positionsEntity.getTime());

            positionsRepository.save(temp);

            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
