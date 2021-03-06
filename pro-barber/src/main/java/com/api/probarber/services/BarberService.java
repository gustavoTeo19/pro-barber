package com.api.probarber.services;

import com.api.probarber.models.BarberModel;
import com.api.probarber.repositories.BarberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarberService {
    final BarberRepository barberRepository;

    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public boolean existsByCpf(String cpf) {
        return barberRepository.existsByCpf(cpf);
    }

    public BarberModel save(BarberModel barberModel) {
        return barberRepository.save(barberModel);
    }

    public List<BarberModel> findAll() {
        return barberRepository.findAllByDelete();
    }

    public Optional<BarberModel> findById(UUID id) {
        return barberRepository.findById(id);
    }
}
