package com.bfiejdasz.fleet_manager_rest_api.repository;

import com.bfiejdasz.fleet_manager_rest_api.entity.ProblemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProblemsRepository extends JpaRepository<ProblemsEntity, Long> {
}
