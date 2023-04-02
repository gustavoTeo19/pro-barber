package com.api.probarber.repositories;

import com.api.probarber.models.AppointmentModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface AppointmentRepository extends  JpaRepository<AppointmentModel, UUID> {

}
