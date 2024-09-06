package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
import com.guardians_of_the_code.exceptions.HandleConflictException;
import com.guardians_of_the_code.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateClientUseCase {
    @Autowired
    private ClientService service;

    public Client execute(ClientRequestDTO client){
        if(client.getName().isEmpty() || client.getName().isBlank()){
            throw new HandleBadRequestException("Nome não pode ser vazio ou nulo");
        }

        if(client.getEmail().isEmpty() || client.getEmail().isBlank()){
            throw new HandleBadRequestException("Email não pode ser vazio ou nulo");
        }

        if(client.getPhone().isEmpty() || client.getPhone().isBlank()){
            throw new HandleBadRequestException("Telefone não pode ser vazio ou nulo");
        }

        if(client.getPassword() == null || client.getPassword().isEmpty()){
            throw new HandleBadRequestException("Senha não pode ser vazia ou nula");
        }

        if(client.getUf() != null){
            if(client.getUf().length() != 2){
                throw new HandleBadRequestException("UF deve  conter 2 caracteres");
            }
        }

        if(client.getName().length() > 30){
            throw new HandleBadRequestException("O nome deve ter até 30 caracteres");
        }

        if(client.getEmail().length() > 120 || client.getEmail().length() < 15){
            throw new HandleBadRequestException("O email deve ter no minimo 15 e no máximo 120 caracteres");
        }

        if(client.getPhone().length() != 11){
            throw new HandleBadRequestException("O telefone deve seguir o formato 99221111111,contendo 11 caracteres");
        }

        if(client.getPassword().length() > 100 || client.getPassword().length() < 8){
            throw new HandleBadRequestException("A senha deve ter no minimo 8 e no máximo 100 caracteres");
        }

        boolean existsEmail = service.existsClientByEmail(client.getEmail());
        boolean existsPhone = service.existsClientByPhone(client.getPhone());

        if(existsEmail){
            throw new HandleConflictException("Email já existe");
        }

        if(existsPhone){
            throw new HandleConflictException("Número de celular já existe");
        }

        return service.createClient(client);
    }
}
