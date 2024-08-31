package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteClientUseCase {
    @Autowired
    private ClientService service;

    public void execute(UUID uuid){
        boolean existsClient = service.existsClient(uuid);

        if(!existsClient){
            throw new HandleNotFoundException("Cliente");
        }

        service.deleteClient(uuid);
    }
}
