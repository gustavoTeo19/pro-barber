package com.api.probarber.repositories;

import com.api.probarber.models.AppointmentModel;
import com.api.probarber.models.BarberModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface AppointmentRepository extends  JpaRepository<AppointmentModel, UUID> {
    @Query("select e from #{#entityName} e where e.isDelete = false")
    Page<AppointmentModel> findAllByDelete(Pageable pageable);
}
