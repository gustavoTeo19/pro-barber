package com.api.probarber.controllers;

import com.api.probarber.dtos.ServiceDto;
import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;
import com.api.probarber.services.ServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/services")
public class ServiceController {
    final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<Object> saveService(@RequestBody @Valid ServiceDto serviceDto){
        var serviceModel = new ServiceModel();
        BeanUtils.copyProperties(serviceDto, serviceModel);
        serviceModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        serviceModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.save(serviceModel));
    }

    @GetMapping
    public ResponseEntity<List<ServiceModel>> getAllServices(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOneService(@PathVariable(value = "id") UUID id){
        Optional<ServiceModel> serviceModelOptional = serviceService.findById(id);
        if(!serviceModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceModelOptional.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateService(@PathVariable(value = "id") UUID id, @RequestBody @Valid ServiceDto serviceDto){
        Optional<ServiceModel> serviceModelOptional = serviceService.findById(id);
        if(!serviceModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }
        var serviceModel = new ServiceModel();
        BeanUtils.copyProperties(serviceDto, serviceModel);
        serviceModel.setId(serviceModelOptional.get().getId());
        serviceModel.setRegistrationDate(serviceModelOptional.get().getRegistrationDate());
        serviceModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.save(serviceModel));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteService(@PathVariable(value = "id") UUID id){
        Optional<ServiceModel> serviceModelOptional = serviceService.findById(id);
        if(!serviceModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found");
        }
        var serviceModel = new ServiceModel();
        BeanUtils.copyProperties(serviceModelOptional.get(), serviceModel);
        serviceModel.setId(serviceModelOptional.get().getId());
        serviceModel.setRegistrationDate(serviceModelOptional.get().getRegistrationDate());
        serviceModel.setDelete(true);
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.save(serviceModel));

    }
}
