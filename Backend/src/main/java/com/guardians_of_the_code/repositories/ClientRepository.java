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
        Optional<Client> clientModel = jpaRepository.findClientById(uuid);
        if(clientModel.isEmpty()){
            throw new HandleNotFoundException("Cliente");
        }

        Client existingClient = clientModel.get();

        if (client.getName() != null && !client.getName().isEmpty()) {
            existingClient.setName(client.getName());
        }

        if (client.getCep() != null && !client.getCep().isEmpty()) {
            existingClient.setCep(client.getCep());
        }

        if (client.getEmail() != null && !client.getEmail().isEmpty()) {
            if (jpaRepository.existsByEmailAndIdNot(client.getEmail(), uuid)) {
                throw new RuntimeException("Email já existe");
            }
            existingClient.setEmail(client.getEmail());
        }

        if (client.getPhone() != null && !client.getPhone().isEmpty()) {
            existingClient.setPhone(client.getPhone());
        }

        jpaRepository.save(existingClient);

        return new MessageStatusDTO("Atualizado com sucesso", 200);
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

    @Override
    public boolean existsClientByEmailAndId(UUID uuid,String email) {
        return jpaRepository.existsByIdAndEmail(uuid,email);
    }

}
