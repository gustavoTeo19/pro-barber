package com.api.probarber.controllers;

import com.api.probarber.dtos.ClientDto;
import com.api.probarber.dtos.LoginDto;
import com.api.probarber.models.ClientModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

//@CrossOrigin()
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/security")
public class SecurityController {
    @PostMapping
    public ResponseEntity<Object> validate(){
//        if(clientService.existByCpf(clientDto.getCpf())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
//        }
//        var clientModel = new ClientModel();
//        BeanUtils.copyProperties(clientDto, clientModel);
//        clientModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
//        clientModel.setDelete(false);

        return ResponseEntity.status(HttpStatus.CREATED).body("Login bem sucedido");
    }
}
