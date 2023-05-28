package com.api.probarber.repositories;

import com.api.probarber.models.LoyaltyPlanModel;
import com.api.probarber.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
}
