package com.api.probarber.repositories;

import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceModel, UUID> {
    @Query("select e from #{#entityName} e where e.isDelete = false")
    Page<ServiceModel> findAllByDelete(Pageable pageable);
}
