package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IPositionsRepository extends JpaRepository<PositionsEntity, Long> {
    @Query("SELECT p FROM PositionsEntity p WHERE p.rideid = :id")
    List<PositionsEntity> findAllByRideid(@Param("id") Long rideId);

    @Query("SELECT p FROM PositionsEntity p WHERE p.time IN (SELECT MAX(p2.time) FROM PositionsEntity p2 WHERE p2.rideid = p.rideid) ")
    List<PositionsEntity> findLatestPositionForAllRides();
}
