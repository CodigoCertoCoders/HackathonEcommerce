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

        if(request.getUf().isEmpty() || request.getUf().isBlank()){
            throw new HandleBadRequestException("Uf não pode ser vazia ou nula");
        }

        if(request.getCity().isEmpty() || request.getCity().isBlank()){
            throw new HandleBadRequestException("Cidade não pode ser vazia ou nula");
        }

        if(request.getNeighborhood().isEmpty() || request.getNeighborhood().isBlank()){
            throw new HandleBadRequestException("Bairro não pode ser vazia ou nula");
        }

        if(request.getRoad().isEmpty() || request.getRoad().isBlank()){
            throw new HandleBadRequestException("Rua não pode ser vazia ou nula");
        }

        if(request.getEmail().isEmpty() || request.getEmail().isBlank()){
            throw new HandleBadRequestException("Email não pode ser vazio ou nulo");
        }

        if(request.getPhone().isEmpty() || request.getPhone().isBlank()){
            throw new HandleBadRequestException("Telefone não pode ser vazio ou nulo");
        }

        if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new HandleBadRequestException("Senha não pode ser vazia ou nula");
        }

        if(request.getUf().length() != 2){
            throw new HandleBadRequestException("UF deve  conter 2 caracteres");
        }

        if(request.getName().length() > 30){
            throw new HandleBadRequestException("O nome deve ter até 30 caracteres");
        }

        if(request.getEmail().length() > 120 || request.getEmail().length() < 15){
            throw new HandleBadRequestException("O email deve ter no minimo 15 e no máximo 120 caracteres");
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
