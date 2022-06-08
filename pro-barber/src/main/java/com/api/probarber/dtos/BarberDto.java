package com.api.probarber.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BarberDto {
    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    private String name;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
