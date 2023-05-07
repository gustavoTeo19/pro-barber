package com.api.probarber.controllers;

import com.api.probarber.dtos.ClientDto;
import com.api.probarber.models.ClientModel;
import com.api.probarber.services.LoyaltyPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/loyalty-plan")
public class LoyaltyPlanServiceController {

    final LoyaltyPlanService loyaltyPlanService;

    public LoyaltyPlanServiceController(LoyaltyPlanService loyaltyPlanService) {
        this.loyaltyPlanService = loyaltyPlanService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPlans(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(loyaltyPlanService.findAllByDelete(pageable));
    }

//    @PostMapping
//    public ResponseEntity<Object> savePlan(@RequestBody @Valid ClientDto clientDto){
//        if(clientService.existByCpf(clientDto.getCpf())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
//        }
//        var clientModel = new ClientModel();
//        BeanUtils.copyProperties(clientDto, clientModel);
//        clientModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
//        clientModel.setDelete(false);
//        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
//    }
}
