package com.api.probarber.services;

import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;
import com.api.probarber.repositories.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {
    final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ServiceModel save(ServiceModel serviceModel) {
        return serviceRepository.save(serviceModel);
    }

    public Page<ServiceModel> findAllByDelete(Pageable pageable) {
        return serviceRepository.findAllByDelete(pageable);
    }

    public Optional<ServiceModel> findById(UUID id) {
        return serviceRepository.findById(id);
    }
}
