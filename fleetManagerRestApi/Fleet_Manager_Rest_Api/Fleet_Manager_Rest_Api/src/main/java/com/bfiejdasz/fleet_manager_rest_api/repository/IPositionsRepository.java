package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IPositionsRepository extends JpaRepository<PositionsEntity, Long> {
    @Query("SELECT p FROM PositionsEntity p WHERE p.rideId = :id")
    List<PositionsEntity> findAllByRideid(@Param("id") Long carId);
}
