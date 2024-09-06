package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteRequestUseCase {
    @Autowired
    private RequestService service;

    public void execute(UUID uuid){
        boolean existsRequest = service.existsRequest(uuid);
        if(!existsRequest){
            throw new HandleNotFoundException("Pedido");
        }

        service.deleteRequest(uuid);
    }
}
