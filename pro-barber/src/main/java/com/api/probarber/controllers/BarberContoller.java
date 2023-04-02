package com.api.probarber.controllers;

import com.api.probarber.dtos.BarberDto;
import com.api.probarber.dtos.ClientDto;
import com.api.probarber.models.BarberModel;
import com.api.probarber.models.ServiceModel;
import com.api.probarber.services.BarberService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/barber")
public class BarberContoller {
    final BarberService barberService;

    public BarberContoller(BarberService barberService) {
        this.barberService = barberService;
    }

    @PostMapping
    public ResponseEntity<Object> saveBarber(@RequestBody @Valid BarberDto barberDto){
        if(barberService.existsByCpf(barberDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
        }
        var barberModel = new BarberModel();
        BeanUtils.copyProperties(barberDto, barberModel);
        barberModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        barberModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(barberService.save(barberModel));
    }

    @GetMapping
    public ResponseEntity<List<BarberModel>> getAllBarbers(){
        return ResponseEntity.status(HttpStatus.OK).body(barberService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getBarber(@PathVariable(value = "id") UUID id){
        Optional<BarberModel> barberModelOptional = barberService.findById(id);
        if(!barberModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Barber not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(barberModelOptional.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateBarber(@PathVariable(value = "id") UUID id,@RequestBody @Valid BarberDto barberDto){
        Optional<BarberModel> barberModelOptional = barberService.findById(id);
        if(!barberModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Barber not found!");
        }
        BarberModel barberModel = new BarberModel();
        BeanUtils.copyProperties(barberDto, barberModel);
        barberModel.setId(barberModelOptional.get().getId());
        barberModel.setRegistrationDate(barberModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(barberService.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBarber(@PathVariable(value = "id") UUID id){
        Optional<BarberModel> barberModelOptional = barberService.findById(id);
        if(!barberModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Barber not found!");
        }
        BarberModel barberModel = new BarberModel();
        BeanUtils.copyProperties(barberModelOptional.get(), barberModel);
        barberModel.setId(barberModelOptional.get().getId());
        barberModel.setRegistrationDate(barberModelOptional.get().getRegistrationDate());
        barberModel.setDelete(true);
        return ResponseEntity.status(HttpStatus.OK).body(barberService.save(barberModel));
    }

    }
