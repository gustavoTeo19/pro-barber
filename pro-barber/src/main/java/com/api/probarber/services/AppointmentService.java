package com.api.probarber.services;

import com.api.probarber.models.AppointmentModel;
import com.api.probarber.models.BarberModel;
import com.api.probarber.repositories.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentModel save(AppointmentModel appointmentModel) {
        return appointmentRepository.save(appointmentModel);
    }

    public Optional<AppointmentModel> findById(UUID id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public void delete(AppointmentModel appointmentModel){
        appointmentRepository.delete(appointmentModel);
    }

    public Page<AppointmentModel> findAllByDelete(Pageable pageable) {
        return appointmentRepository.findAllByDelete(pageable);
    }

}
