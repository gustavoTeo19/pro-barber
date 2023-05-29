package com.api.probarber.dtos;

import javax.validation.constraints.NotBlank;

public class RolesResponseDto {
    @NotBlank
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
