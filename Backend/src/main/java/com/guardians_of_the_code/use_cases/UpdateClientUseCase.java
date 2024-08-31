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
        if(request.getName().isEmpty() || request.getName().isBlank()){
            throw new HandleBadRequestException("Nome não pode ser vazio ou nulo");
        }

        if(request.getCep().isEmpty() || request.getCep().isBlank()){
            throw new HandleBadRequestException("Cep não pode ser vazio ou nulo");
        }

        if(request.getEmail().isEmpty() || request.getEmail().isBlank()){
            throw new HandleBadRequestException("Email não pode ser vazio ou nulo");
        }

        if(request.getPhone().isEmpty() || request.getPhone().isBlank()){
            throw new HandleBadRequestException("Telefone não pode ser vazio ou nulo");
        }

        if(request.getCep().length() != 8){
            throw new HandleBadRequestException("O cep deve seguir o formato 00000000,contendo 8 caracteres");
        }

        if(request.getName().length() > 30 || request.getName().length() < 4){
            throw new HandleBadRequestException("O nome deve ter no minimo 4 e no máximo 30 caracteres");
        }

        if(request.getEmail().length() > 120){
            throw new HandleBadRequestException("O email deve ter até 120 caracteres");
        }

        if(request.getPhone().length() != 11){
            throw new HandleBadRequestException("O cep deve seguir o formato 99221111111,contendo 11 caracteres");
        }

        if(request.getPassword().length() > 100 || request.getPassword().length() < 8){
            throw new HandleBadRequestException("A senha deve ter no minimo 8 e no máximo 100 caracteres");
        }

        boolean existsPhone = service.existsClientByPhone(request.getPhone());
        if(existsPhone){
            throw new HandleConflictException("Número de celular já existe");
        }

        boolean existsByEmailAndId = service.existsClientByEmailAndId(uuid, request.getEmail());
        if(existsByEmailAndId){
            throw new HandleConflictException("Email já existe");
        }

        return service.updateClient(uuid, request);
    }
}
