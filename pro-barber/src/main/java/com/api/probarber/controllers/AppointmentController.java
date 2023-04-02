package com.api.probarber.controllers;

import com.api.probarber.dtos.AppointmentDto;
import com.api.probarber.models.AppointmentModel;
import com.api.probarber.models.BarberModel;
import com.api.probarber.models.ClientModel;
import com.api.probarber.services.AppointmentService;
import com.api.probarber.services.BarberService;
import com.api.probarber.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/appointment")
public class AppointmentController {
    final AppointmentService appointmentService;

    final BarberService barberService;

    final ClientService clientService;

    public AppointmentController(AppointmentService appointmentService, BarberService barberService,
                                 ClientService clientService) {
        this.appointmentService = appointmentService;
        this.barberService = barberService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentDto appointmentDto){
        var appointmentModel = new AppointmentModel();

        System.out.println(appointmentDto);
        UUID barberId = appointmentDto.getBarberId();
        UUID clientId = appointmentDto.getClienteId();

        Optional<BarberModel> barberModel = barberService.findById(barberId);
        Optional<ClientModel> clientModel = clientService.findByid(clientId);

        System.out.println(clientModel);
        System.out.println(barberModel);

        appointmentModel.setAppoitmentDate(appointmentDto.getAppoitmentDate());
        appointmentModel.setServices(appointmentDto.getServices());
        appointmentModel.setCliente(clientModel.get());
        appointmentModel.setBarber(barberModel.get());
        appointmentModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        appointmentModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointmentModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable(value = "id") UUID id){
        Optional<AppointmentModel> appointmentModelOptional = appointmentService.findById(id);
        if(!appointmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Appointment not found!");
        }
        appointmentService.delete(appointmentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado");
    }
}
