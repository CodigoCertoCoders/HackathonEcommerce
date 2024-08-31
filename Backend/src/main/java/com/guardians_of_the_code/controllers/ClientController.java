package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.use_cases.CreateClientUseCase;
import com.guardians_of_the_code.use_cases.FindClientByUUIDUseCase;
import com.guardians_of_the_code.use_cases.UpdateClientUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private FindClientByUUIDUseCase findClientByUUIDUseCase;

    @Autowired
    private CreateClientUseCase createClientUseCase;

    @Autowired
    private UpdateClientUseCase updateClientUseCase;

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID uuid){
        ClientResponseDTO clientResponseDTO = findClientByUUIDUseCase.execute(uuid);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @PostMapping
    public ResponseEntity<MessageStatusDTO> createClient(@RequestBody ClientRequestDTO client, UriComponentsBuilder uriBuilder){
        Client clientModel = createClientUseCase.execute(client);
        UUID clientId = clientModel.getId();
        URI path = uriBuilder.path("/clients/{id}").buildAndExpand(clientId).toUri();

        MessageStatusDTO response = new MessageStatusDTO("Cliente criado com sucesso",201);

        return ResponseEntity.created(path).body(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<MessageStatusDTO> upadteClient(@PathVariable UUID uuid,@RequestBody ClientRequestDTO client){
        MessageStatusDTO response = updateClientUseCase.execute(uuid,client);
        return ResponseEntity.ok(response);
    }
}