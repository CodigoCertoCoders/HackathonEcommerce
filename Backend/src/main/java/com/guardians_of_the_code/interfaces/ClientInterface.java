package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;

import java.util.UUID;

public interface ClientInterface {
    ClientResponseDTO findByClient(UUID uuid);
    Client createClient(ClientRequestDTO client);
    MessageStatusDTO updateClient(UUID uuid,ClientRequestDTO client);
    void deleteClient(UUID uuid);
    boolean updateTokenClient(String email,String token);
    boolean existsClientByEmail(String email);
    boolean existsClient(UUID uuid);
    boolean existsClientByEmailAndId(UUID uuid,String email);
}
