package com.guardians_of_the_code.services;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.interfaces.ClientInterface;
import com.guardians_of_the_code.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService implements ClientInterface {
    @Autowired
    private ClientRepository repository;

    @Override
    public ClientResponseDTO findByClient(UUID uuid) {
        return repository.findByClient(uuid);
    }

    @Override
    public Client createClient(ClientRequestDTO client) {
        return repository.createClient(client);
    }

    @Override
    public MessageStatusDTO updateClient(UUID uuid, ClientRequestDTO client) {
        return null;
    }

    @Override
    public MessageStatusDTO deleteClient(UUID uuid) {
        return null;
    }
    @Override
    public boolean existsClientByEmail(String email) {
        return repository.existsClientByEmail(email);
    }

    @Override
    public boolean existsClient(UUID uuid) {
        return repository.existsClient(uuid);
    }
}
