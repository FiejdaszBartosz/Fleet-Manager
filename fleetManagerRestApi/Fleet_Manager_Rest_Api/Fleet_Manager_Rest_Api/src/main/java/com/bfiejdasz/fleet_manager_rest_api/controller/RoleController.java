package com.bfiejdasz.fleet_manager_rest_api.controller;

import com.bfiejdasz.fleet_manager_rest_api.entity.RoleEntity;
import com.bfiejdasz.fleet_manager_rest_api.repository.EmployeesRepository;
import com.bfiejdasz.fleet_manager_rest_api.repository.IRoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("role")
public class RoleController {
    private final IRoleRepository roleRepository;

    public RoleController(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public ResponseEntity<RoleEntity> getRoleById(@RequestParam("id") long id) {
        Optional<RoleEntity> optionalRole = roleRepository.findById(id);

        if(optionalRole.isPresent()) {
            RoleEntity role = optionalRole.get();
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
