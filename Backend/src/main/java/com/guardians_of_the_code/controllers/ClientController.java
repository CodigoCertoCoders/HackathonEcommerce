package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.ClientRequestDTO;
import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.use_cases.CreateClientUseCase;
import com.guardians_of_the_code.use_cases.DeleteClientUseCase;
import com.guardians_of_the_code.use_cases.FindClientByUUIDUseCase;
import com.guardians_of_the_code.use_cases.UpdateClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Autowired
    private DeleteClientUseCase deleteClientUseCase;

    @GetMapping("/{uuid}")
    @Operation(
            summary = "/clients/{uuid}",
            description = "Buscar um cliente pelo uuid",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Cliente encontrado"),
                    @ApiResponse(responseCode = "404",description = "Cliente não encontrado"),
                    @ApiResponse(responseCode = "401",description = "Não autorizado")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID uuid){
        ClientResponseDTO clientResponseDTO = findClientByUUIDUseCase.execute(uuid);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @PostMapping
    @Operation(
            summary = "/clients/",
            description = "Criar um cliente",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Cliente criado com sucesso"),
                    @ApiResponse(responseCode = "400",description = "Erros na requisição"),
                    @ApiResponse(responseCode = "409",description = "Conflito no banco de dados")
            }
    )
    public ResponseEntity<MessageStatusDTO> createClient(@RequestBody ClientRequestDTO client, UriComponentsBuilder uriBuilder){
        Client clientModel = createClientUseCase.execute(client);
        UUID clientId = clientModel.getId();
        URI path = uriBuilder.path("/clients/{id}").buildAndExpand(clientId).toUri();

        MessageStatusDTO response = new MessageStatusDTO("Cliente criado com sucesso",201);

        return ResponseEntity.created(path).body(response);
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "/clients/{uuid}",
            description = "Atualizar cliente",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "400",description = "Erros na requisição"),
                    @ApiResponse(responseCode = "401",description = "Não autorizado"),
                    @ApiResponse(responseCode = "404",description = "Cliente não encontrado"),
                    @ApiResponse(responseCode = "409",description = "Conflito no banco de dados")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MessageStatusDTO> upadteClient(@PathVariable UUID uuid,@RequestBody ClientRequestDTO client){
        MessageStatusDTO response = updateClientUseCase.execute(uuid,client);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "/clients/{uuid}",
            description = "Deletar cliente",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Cliente deletado com sucesso"),
                    @ApiResponse(responseCode = "401",description = "Não autorizado"),
                    @ApiResponse(responseCode = "404",description = "Cliente não encontrado")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID uuid){
        deleteClientUseCase.execute(uuid);

        return ResponseEntity.noContent().build();
    }
}