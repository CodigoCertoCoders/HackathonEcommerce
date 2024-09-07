package com.guardians_of_the_code.repositories;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.interfaces.ClientInterface;
import com.guardians_of_the_code.interfaces.JPAInterfaceClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepository implements ClientInterface {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JPAInterfaceClient jpaRepository;

    @Autowired
    private PasswordEncoder encoder;

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

        clientModel.setPassword(encoder.encode(client.getPassword()));

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

        if (client.getUf() != null && !client.getUf().isEmpty()) {
            existingClient.setUf(client.getUf());
        }

        if (client.getCity() != null && !client.getCity().isEmpty()) {
            existingClient.setCity(client.getCity());
        }

        if (client.getNeighborhood() != null && !client.getNeighborhood().isEmpty()) {
            existingClient.setNeighborhood(client.getNeighborhood());
        }

        if (client.getRoad() != null && !client.getRoad().isEmpty()) {
            existingClient.setRoad(client.getRoad());
        }

        if (client.getNumber_house() != null && !client.getNumber_house().isEmpty()) {
            existingClient.setNumber_house(client.getNumber_house());
        }

        if (client.getComplement() != null && !client.getComplement().isEmpty()) {
            existingClient.setComplement(client.getComplement());
        }

        if (client.getEmail() != null && !client.getEmail().isEmpty()) {
            if (jpaRepository.existsByEmailAndIdNot(client.getEmail(), uuid)) {
                throw new RuntimeException("Email já existe");
            }
            existingClient.setEmail(client.getEmail());
        }

        if(client.getPassword() != null && !client.getPassword().isEmpty()){
            existingClient.setPassword(encoder.encode(client.getPassword()));
        }

        jpaRepository.save(existingClient);

        return new MessageStatusDTO("Atualizado com sucesso", 200);
    }

    @Override
    public void deleteClient(UUID uuid) {
        jpaRepository.deleteById(uuid);
    }

    @Override
    public boolean updateTokenClient(String email, String token) {
        Optional<Client> optionalClient = jpaRepository.findByEmail(email);
        if (optionalClient.isEmpty()) {
            throw new HandleNotFoundException("Cliente");
        }
        Client client = optionalClient.get();
        client.setToken(token);
        jpaRepository.save(client);
        return true;
    }

    @Override
    public boolean existsClientByEmail(String email) {
        return jpaRepository.existsClientByEmail(email);
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
