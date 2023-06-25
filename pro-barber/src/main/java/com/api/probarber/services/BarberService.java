package com.api.probarber.services;

import com.api.probarber.dtos.BarberResponseDto;
import com.api.probarber.models.BarberModel;
import com.api.probarber.repositories.BarberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
        return barberRepository.findAll();
    }

    public Page<BarberModel> findAllByDelete(Pageable pageable) {
        return barberRepository.findAllByDelete(pageable);
    }

    public List<BarberResponseDto> findAllByDeleteOnlyName() {
        ArrayList<BarberResponseDto> barbers = new ArrayList<>();
        for (BarberModel bm: barberRepository.findAllByDeleteOnlyName() ) {
            BarberResponseDto barberResponseDto = new BarberResponseDto();
            barberResponseDto.setName(bm.getName());
            barberResponseDto.setId(bm.getId());
            barbers.add(barberResponseDto);
        }
        return barbers;
    }

    public Optional<BarberModel> findById(UUID id) {
        return barberRepository.findById(id);
    }
}
