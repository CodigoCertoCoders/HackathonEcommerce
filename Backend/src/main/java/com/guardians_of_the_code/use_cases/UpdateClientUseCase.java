package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.exceptions.HandleConflictException;
import com.guardians_of_the_code.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateClientUseCase {
    @Autowired
    private ClientService service;

    public MessageStatusDTO execute(UUID uuid, ClientRequestDTO request){
        boolean existsByEmailAndId = service.existsClientByEmailAndId(uuid, request.getEmail());

        if(existsByEmailAndId){
            throw new HandleConflictException("Email já existe");
        }

        return service.updateClient(uuid, request);
    }
}
