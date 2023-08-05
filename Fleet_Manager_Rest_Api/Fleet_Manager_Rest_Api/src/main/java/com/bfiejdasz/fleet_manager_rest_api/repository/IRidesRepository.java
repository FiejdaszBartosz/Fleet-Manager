package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.RidesEmployeesEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.RidesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRidesRepository extends JpaRepository<RidesEntity, Long> {
    @Query("SELECT p FROM RidesEntity p WHERE p.rideId = :id")
    RidesEntity findByRideid(@Param("id") Long carId);

    @Query("SELECT r FROM RidesEntity r")
    List<RidesEntity> findAllRides();
}
