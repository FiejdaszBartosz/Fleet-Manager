package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.ProblemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProblemsRepository extends JpaRepository<ProblemsEntity, Long> {
    @Query("SELECT p FROM ProblemsEntity p WHERE p.rideId = :id")
    List<ProblemsEntity> findProblemsByRideid(@Param("id") Long rideId);
}
