package com.api.probarber.services;

import com.api.probarber.models.ClientModel;
import com.api.probarber.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ClientModel> findAllByDelete(Pageable pageable) {
        return clientRepository.findAllByDelete(pageable);
    }

    public Optional<ClientModel> findByid(UUID id) {
        return clientRepository.findById(id);
    }

    public boolean existByCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    public boolean existByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
