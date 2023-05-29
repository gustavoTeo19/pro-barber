package com.api.probarber.controllers;

import com.api.probarber.dtos.BarberDto;
import com.api.probarber.dtos.RolesResponseDto;
import com.api.probarber.dtos.UserValidateDto;
import com.api.probarber.models.UserModel;
import com.api.probarber.repositories.UserRepository;
import com.api.probarber.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/security")
public class SecurityController {
    final UserRepository userRepository;

    public SecurityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    public ResponseEntity<Object> validate(@RequestBody @Valid UserValidateDto userValidateDto){

        byte[] decodedBytes = Base64.getDecoder().decode(userValidateDto.getUser());
        String dadoDecodificado = new String(decodedBytes);
        Optional<UserModel> userModal = userRepository.findByUsername(dadoDecodificado);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModal.get().getRoles());
    }
}
