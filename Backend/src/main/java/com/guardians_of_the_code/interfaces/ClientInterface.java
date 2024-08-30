package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;

import java.util.UUID;

public interface ClientInterface {
    public ClientResponseDTO findByClient(UUID uuid);
    public Client createClient(ClientRequestDTO client);
    public MessageStatusDTO updateClient(UUID uuid,ClientRequestDTO client);
    public MessageStatusDTO deleteClient(UUID uuid);
    public boolean existsClientByEmail(String email);
    public boolean existsClientByPhone(String phone);
    public boolean existsClient(UUID uuid);
}
