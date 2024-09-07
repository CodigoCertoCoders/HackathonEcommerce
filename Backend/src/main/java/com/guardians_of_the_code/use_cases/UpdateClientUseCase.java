package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
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
        if(request.getEmail().isEmpty() || request.getEmail().isBlank()){
            throw new HandleBadRequestException("Email não pode ser vazio ou nulo");
        }

        if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new HandleBadRequestException("Senha não pode ser vazia ou nula");
        }

        if(request.getUf() != null){
            if(request.getUf().length() != 2){
                throw new HandleBadRequestException("UF deve  conter 2 caracteres");
            }
        }

        if(request.getEmail().length() > 120 || request.getEmail().length() < 15){
            throw new HandleBadRequestException("O email deve ter no minimo 15 e no máximo 120 caracteres");
        }

        if(request.getPassword().length() > 100 || request.getPassword().length() < 8){
            throw new HandleBadRequestException("A senha deve ter no minimo 8 e no máximo 100 caracteres");
        }

        boolean existsByEmailAndId = service.existsClientByEmailAndId(uuid, request.getEmail());
        if(existsByEmailAndId){
            throw new HandleConflictException("Email já existe");
        }

        return service.updateClient(uuid, request);
    }
}
