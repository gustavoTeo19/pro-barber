package com.api.probarber.controllers;

import com.api.probarber.dtos.ClientDto;
import com.api.probarber.models.ClientModel;
import com.api.probarber.models.RoleModel;
import com.api.probarber.models.UserModel;
import com.api.probarber.services.ClientService;
import com.api.probarber.services.RoleService;
import com.api.probarber.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
public class ClientController {
    final ClientService clientService;

    final RoleService roleService;

    final UserService userService;

    public ClientController(ClientService clientService, RoleService roleService, UserService userService) {
        this.clientService = clientService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto){
        if(clientService.existByCpf(clientDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
        }
        if(clientService.existByEmail(clientDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
        }
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        clientModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        clientModel.setDelete(false);

        List<RoleModel> roles = new ArrayList<>();
        Optional<RoleModel> rolesOpt = roleService.findById(UUID.fromString("21356e11-300b-4a0d-9513-62cdfaba79f2"));
        rolesOpt.ifPresent(roles::add);
        UserModel userModel = new UserModel();
        userModel.setCpf(clientModel.getCpf());
        userModel.setUsername(clientModel.getEmail());
        userModel.setPassword(new BCryptPasswordEncoder().encode(clientDto.getPassword()));
        userModel.setRoles(roles);

        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllClients(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllByDelete(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> clientModelOptional = clientService.findByid(id);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientDto clientDto){
        Optional<ClientModel> clientModelOptional = clientService.findByid(id);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        clientModel.setId(clientModelOptional.get().getId());
        clientModel.setRegistrationDate(clientModelOptional.get().getRegistrationDate());
        clientModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id){
//        Optional<ClientModel> clientModelOptional = clientService.findByid(id);
//        if(!clientModelOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
//        }
//    }
}
