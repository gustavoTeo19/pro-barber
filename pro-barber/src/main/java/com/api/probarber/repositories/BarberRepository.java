package com.api.probarber.repositories;

import com.api.probarber.dtos.BarberResponseDto;
import com.api.probarber.models.BarberModel;
import com.api.probarber.models.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BarberRepository extends JpaRepository<BarberModel, UUID> {
    boolean existsByCpf(String cpf);

    @Query("select e from #{#entityName} e where e.isDelete = false")
    Page<BarberModel> findAllByDelete(Pageable pageable);

    @Query("select e from #{#entityName} e where e.isDelete = false")
    List<BarberModel> findAllByDeleteOnlyName();



}
