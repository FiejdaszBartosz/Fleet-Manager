package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.PositionsEntity;
import com.bfiejdasz.fleet_manager_rest_api.entity.RidesEmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRidesEmployeesRepository extends JpaRepository<RidesEmployeesEntity, Long> {
    @Query("SELECT p FROM RidesEmployeesEntity p WHERE p.rideId = :id")
    List<RidesEmployeesEntity> findAllByRideid(@Param("id") Long carId);

    @Query("SELECT p FROM RidesEmployeesEntity p WHERE p.idEmployee = :id")
    List<RidesEmployeesEntity> findAllByEmployeeId(@Param("id") Long carId);
}
