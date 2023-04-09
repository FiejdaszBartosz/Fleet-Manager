package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.ProblemsEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IProblemsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("problems")
public class ProblemsController {
    private final IProblemsRepository problemsRepository;

    public ProblemsController(IProblemsRepository problemsRepository) {
        this.problemsRepository = problemsRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProblemsEntity>> getAll() {
        List<ProblemsEntity> output = problemsRepository.findAll();

        if(output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(output);
        }
    }

    @GetMapping("")
    public ResponseEntity<ProblemsEntity> getProblemById(@RequestParam("id") long id) {
        Optional<ProblemsEntity> optional = problemsRepository.findById(id);

        if(optional.isPresent()) {
            ProblemsEntity output = optional.get();
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<ProblemsEntity> problemsEntities) {
        try {
            problemsRepository.saveAll(problemsEntities);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("")
    public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody ProblemsEntity problemsEntity) {
        Optional<ProblemsEntity> optional = problemsRepository.findById(id);

        if (optional.isPresent()) {
            ProblemsEntity temp = optional.get();

            if(problemsEntity.getRideId() != null) temp.setRideId(problemsEntity.getRideId());
            if(problemsEntity.getProblemType() != null) temp.setProblemType(problemsEntity.getProblemType());
            if(problemsEntity.getDescription() != null) temp.setDescription(problemsEntity.getDescription());
            if(problemsEntity.getIsSolved() != null) temp.setIsSolved(problemsEntity.getIsSolved());

            problemsRepository.save(temp);

            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> delete(@RequestParam("id") long id) {
        Optional<ProblemsEntity> optional = problemsRepository.findById(id);

        if (optional.isPresent()) {
            problemsRepository.delete(optional.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
