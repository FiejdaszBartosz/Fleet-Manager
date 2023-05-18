package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.RidesEmployeesEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.RidesEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IRidesEmployeesRepository;
import com.bfiejdasz.fleet_manager_rest_api.repository.IRidesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rides")
public class RidesController {
    private final IRidesRepository ridesRepository;

    public RidesController(IRidesRepository ridesRepository) {
        this.ridesRepository = ridesRepository;

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RidesEntity>> getAllRides() {
        List<RidesEntity> rides = ridesRepository.findAll();
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<RidesEntity> getRidesByRidesid(@RequestParam("id") long id) {
        RidesEntity ride = ridesRepository.findByRideid(id);

        if (ride != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ride);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody RidesEntity ride) {
        try {
            RidesEntity rideee = ride;
            ridesRepository.save(ride);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("")
    public ResponseEntity<RidesEntity> updateVehicle(@RequestParam("id") long id, @RequestBody RidesEntity temp) {
        RidesEntity ride  = ridesRepository.findByRideid(id);

        if (ride != null) {
            if(ride.getVehicle() != null) temp.setVehicle(ride.getVehicle());
            if(ride.getStartTime() != null) temp.setStartTime(ride.getStartTime());
            if(ride.getStopTime() != null) temp.setStopTime(ride.getStopTime());
            if(ride.getStartKm() != null) temp.setStartKm(ride.getStartKm());
            if(ride.getStopKm() != null) temp.setStopKm(ride.getStopKm());
            if(ride.getStartFuel() != null) temp.setStartFuel(ride.getStartFuel());
            if(ride.getStopFuel() != null) temp.setStopFuel(ride.getStopFuel());

            ridesRepository.save(temp);

            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteVehicle(@RequestParam("id") long id) {
        RidesEntity ride  = ridesRepository.findByRideid(id);

        if (ride != null) {
            ridesRepository.delete(ride);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
