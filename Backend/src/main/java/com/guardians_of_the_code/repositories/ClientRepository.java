package com.guardians_of_the_code.repositories;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.interfaces.ClientInterface;
import com.guardians_of_the_code.interfaces.JPAInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepository implements ClientInterface {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JPAInterface jpaRepository;

    @Override
    public ClientResponseDTO findByClient(UUID uuid) {
        Optional<Client> clientModel = jpaRepository.findClientById(uuid);
        if(clientModel.isEmpty()){
            throw new HandleNotFoundException("Cliente");
        }
        return modelMapper.map(clientModel, ClientResponseDTO.class);
    }

    @Override
    public Client createClient(ClientRequestDTO client) {
        Client clientModel = modelMapper.map(client,Client.class);

        jpaRepository.save(clientModel);

        return clientModel;
    }

    @Override
    public MessageStatusDTO updateClient(UUID uuid,ClientRequestDTO client) {
        return null;
    }

    @Override
    public MessageStatusDTO deleteClient(UUID uuid) {
        return null;
    }

    @Override
    public boolean existsClientByEmail(String email) {
        return jpaRepository.existsClientByEmail(email);
    }

    @Override
    public boolean existsClientByPhone(String phone) {
        return jpaRepository.existsClientByPhone(phone);
    }

    @Override
    public boolean existsClient(UUID uuid) {
        return jpaRepository.existsClientById(uuid);
    }
}
