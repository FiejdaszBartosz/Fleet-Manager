package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.PlanedRepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlanedRepairsRepository extends JpaRepository<PlanedRepairsEntity, Long> {
    @Query("SELECT p FROM PlanedRepairsEntity p WHERE p.vehicle = :id")
    List<PlanedRepairsEntity> findAllByVehicleId(@Param("id") Long carId);
}
