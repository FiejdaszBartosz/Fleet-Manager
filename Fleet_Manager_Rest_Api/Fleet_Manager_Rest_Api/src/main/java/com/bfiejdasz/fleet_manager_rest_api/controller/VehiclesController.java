package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.VehiclesEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.IVehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vehicles")
public class VehiclesController {

    private final IVehicleRepository vehiclesRepository;

    public VehiclesController(IVehicleRepository vehiclesRepository) {
        this.vehiclesRepository = vehiclesRepository;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<VehiclesEntity>> getAllVehicles() {
        List<VehiclesEntity> vehicles = vehiclesRepository.findAll();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<VehiclesEntity> getVehicleById(@RequestParam("id") long id) {
        Optional<VehiclesEntity> optional = vehiclesRepository.findById(id);

        if(optional.isPresent()) {
            VehiclesEntity output = optional.get();
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody List<VehiclesEntity> vehicles) {
        try {
            vehiclesRepository.saveAll(vehicles);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("")
    public ResponseEntity<VehiclesEntity> updateVehicle(@RequestParam("id") long id, @RequestBody VehiclesEntity vehicle) {
        Optional<VehiclesEntity> optional = vehiclesRepository.findById(id);

        if (optional.isPresent()) {
            VehiclesEntity temp = optional.get();

            if(vehicle.getManufacture() != null) temp.setManufacture(vehicle.getManufacture());
            if(vehicle.getModel() != null) temp.setModel(vehicle.getModel());
            if(vehicle.getYear() != null) temp.setYear(vehicle.getYear());
            if(vehicle.getVin() != null) temp.setVin(vehicle.getVin());
            if(vehicle.getLicensePlate() != null) temp.setLicensePlate(vehicle.getLicensePlate());
            if(vehicle.getMileage() != null) temp.setMileage(vehicle.getMileage());
            if(vehicle.getInsurance() != null) temp.setInsurance(vehicle.getInsurance());
            if(vehicle.getInUse() != null) temp.setInUse(vehicle.getInUse());

            vehiclesRepository.save(temp);

            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteVehicle(@RequestParam("id") long id) {
        Optional<VehiclesEntity> optional = vehiclesRepository.findById(id);

        if (optional.isPresent()) {
            vehiclesRepository.delete(optional.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
