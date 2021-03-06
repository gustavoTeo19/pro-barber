package com.api.probarber.services;

import com.api.probarber.models.ClientModel;
import com.api.probarber.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAllByDelete();
    }

    public Optional<ClientModel> findByid(UUID id) {
        return clientRepository.findById(id);
    }

    public boolean existByCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }
}
