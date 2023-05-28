package com.api.probarber.services;

import com.api.probarber.models.RoleModel;
import com.api.probarber.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<RoleModel> findById(UUID id) {
        return roleRepository.findById(id);
    }



}
