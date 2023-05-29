package com.api.probarber.dtos;

import javax.validation.constraints.NotBlank;

public class UserValidateDto {
    @NotBlank
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
