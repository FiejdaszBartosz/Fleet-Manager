package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.VehiclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IVehicleRepository extends JpaRepository<VehiclesEntity, Long> {
    @Query("SELECT p FROM VehiclesEntity p WHERE p.licensePlate = :licensePlate")
    Optional<VehiclesEntity> findByLicensePlate(@Param("licensePlate") String licensePlate);
}
