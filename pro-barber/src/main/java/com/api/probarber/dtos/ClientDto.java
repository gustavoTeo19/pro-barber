package com.api.probarber.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class ClientDto {

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    private String name;

    @NotBlank
    private String cellPhone;

    @NotBlank
    private String address;

    @NotBlank
    private String password;

    @NotBlank
    private String email;


}
