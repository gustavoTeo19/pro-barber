package com.api.probarber.controllers;

import com.api.probarber.dtos.AppointmentDto;
import com.api.probarber.models.AppointmentModel;
import com.api.probarber.models.BarberModel;
import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;
import com.api.probarber.services.AppointmentService;
import com.api.probarber.services.BarberService;
import com.api.probarber.services.ClientService;
import com.api.probarber.services.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/appointment")
public class AppointmentController {
    final AppointmentService appointmentService;

    final BarberService barberService;

    final ClientService clientService;
    final ServiceService serviceService;

    public AppointmentController(AppointmentService appointmentService, BarberService barberService,
                                 ClientService clientService, ServiceService serviceService) {
        this.appointmentService = appointmentService;
        this.barberService = barberService;
        this.clientService = clientService;
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentDto appointmentDto){
        var appointmentModel = new AppointmentModel();

        System.out.println(appointmentDto);
        UUID barberId = appointmentDto.getBarberId();
        UUID clientId = appointmentDto.getClienteId();

        Optional<BarberModel> barberModel = barberService.findById(barberId);
        Optional<ClientModel> clientModel = clientService.findByid(clientId);

        List<ServiceModel> services = new ArrayList<>();
        for(UUID ids: appointmentDto.getServices()){
            Optional<ServiceModel> serviceOpt = serviceService.findById(ids);
            serviceOpt.ifPresent(services::add);
        }

        System.out.println(clientModel);
        System.out.println(barberModel);
        BarberModel barberModel1 = new BarberModel();
        barberModel1.setName(barberModel.get().getName());
        barberModel1.setId(barberModel.get().getId());
        barberModel1.setDelete(barberModel.get().getDelete());
        barberModel1.setCpf(barberModel.get().getCpf());
        barberModel1.setRegistrationDate(barberModel.get().getRegistrationDate());

        ClientModel clientModel1 = new ClientModel();
        clientModel1.setCpf(clientModel.get().getCpf());
        clientModel1.setDelete(clientModel.get().getDelete());
        clientModel1.setName(clientModel.get().getName());
        clientModel1.setId(clientModel.get().getId());
        clientModel1.setAddress(clientModel.get().getAddress());
        clientModel1.setCellPhone(clientModel.get().getCellPhone());
        clientModel1.setRegistrationDate(clientModel.get().getRegistrationDate());

        appointmentModel.setAppoitmentDate(appointmentDto.getAppoitmentDate());
        appointmentModel.setServices(services);
        appointmentModel.setCliente(clientModel1);
        appointmentModel.setBarber(barberModel1);
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
