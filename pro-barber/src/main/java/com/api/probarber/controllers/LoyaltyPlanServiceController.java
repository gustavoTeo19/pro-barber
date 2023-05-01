package com.api.probarber.controllers;

import com.api.probarber.services.LoyaltyPlanService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
