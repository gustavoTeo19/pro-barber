package com.api.probarber.controllers;

import com.api.probarber.dtos.ServiceDto;
import com.api.probarber.models.ServiceModel;
import com.api.probarber.services.ImageUploadService;
import com.api.probarber.services.ServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/services")
public class ServiceController {
    final ServiceService serviceService;
    final ImageUploadService imageUploadService;

    public ServiceController(ServiceService serviceService, ImageUploadService imageUploadService) {
        this.serviceService = serviceService;
        this.imageUploadService = imageUploadService;
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
    public ResponseEntity<Object> getAllServices(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.findAllByDelete(pageable));
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

    @PostMapping("/up")
    public ResponseEntity<Object> upImage(@RequestPart("image") MultipartFile image){


        String imgU = imageUploadService.uploadImg(image);
        return ResponseEntity.status(HttpStatus.OK).body(imgU);

    }
}
