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
    public ResponseEntity<RidesEntity> updateRideById(@RequestParam("id") long id, @RequestBody RidesEntity temp) {
        RidesEntity ride  = ridesRepository.findByRideid(id);

        if (ride != null) {
            if(temp.getVehicle() != null) ride.setVehicle(temp.getVehicle());
            if(temp.getStartTime() != null) ride.setStartTime(temp.getStartTime());
            if(temp.getStopTime() != null) ride.setStopTime(temp.getStopTime());
            if(temp.getStartKm() != null) ride.setStartKm(temp.getStartKm());
            if(temp.getStopKm() != null) ride.setStopKm(temp.getStopKm());
            if(temp.getStartFuel() != null) ride.setStartFuel(temp.getStartFuel());
            if(temp.getStopFuel() != null) ride.setStopFuel(temp.getStopFuel());

            ridesRepository.save(ride);

            return ResponseEntity.ok(ride);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteRide(@RequestParam("id") long id) {
        RidesEntity ride  = ridesRepository.findByRideid(id);

        if (ride != null) {
            ridesRepository.delete(ride);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
