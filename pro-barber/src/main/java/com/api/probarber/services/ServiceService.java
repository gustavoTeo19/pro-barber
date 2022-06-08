package com.api.probarber.services;

import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;
import com.api.probarber.repositories.ServiceRepository;
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

    public List<ServiceModel> findAll() {
        return serviceRepository.findAllByDelete();
    }

    public Optional<ServiceModel> findById(UUID id) {
        return serviceRepository.findById(id);
    }
}
