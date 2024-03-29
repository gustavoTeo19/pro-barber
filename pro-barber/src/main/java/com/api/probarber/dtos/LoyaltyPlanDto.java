package com.api.probarber.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class LoyaltyPlanDto {
    @NotNull
    private Double discount;
    @NotNull
    private int necessaryAmount;


}
