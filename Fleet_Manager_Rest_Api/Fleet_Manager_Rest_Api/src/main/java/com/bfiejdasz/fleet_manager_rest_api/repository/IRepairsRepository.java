package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.RepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRepairsRepository extends JpaRepository<RepairsEntity, Long> {
    @Query("SELECT p FROM RepairsEntity p WHERE p.vehicle = :id")
    List<RepairsEntity> findAllByVehicleId(@Param("id") Long carId);
}
