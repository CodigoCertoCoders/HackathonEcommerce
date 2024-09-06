package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.dtos.RequestResDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.use_cases.DeleteClientUseCase;
import com.guardians_of_the_code.use_cases.GetRequestUseCase;
import com.guardians_of_the_code.use_cases.SaveRequestUseCase;
import com.guardians_of_the_code.use_cases.UpdateRequestUseCase;
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
@RequestMapping("/requests")
@SecurityRequirement(name = "bearerAuth")
public class RequestController {
    @Autowired
    private SaveRequestUseCase saveRequestUseCase;

    @Autowired
    private GetRequestUseCase getRequestUseCase;

    @Autowired
    private UpdateRequestUseCase updateRequestUseCase;

    @Autowired
    private DeleteClientUseCase deleteClientUseCase;

    @PostMapping
    @Operation(
            summary = "/requests/",
            description = "Criar pedido",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Pedido atualiazado com sucesso"),
                    @ApiResponse(responseCode = "404",description = "Pedido não encontrado"),
                    @ApiResponse(responseCode = "400",description = "Erro na requisição")
            }
    )
    public ResponseEntity<MessageStatusDTO> saveRequest(@RequestBody RequestReqDTO request, UriComponentsBuilder uriBuilder){
        Request requestModel = saveRequestUseCase.execute(request);
        URI path = uriBuilder.buildAndExpand("/requests/"+requestModel).toUri();
        MessageStatusDTO response = new MessageStatusDTO("pedido salvo",201);

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "/requests/{uuid}",
            description = "Buscar pedido por uuid",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Pedido encontrado"),
                    @ApiResponse(responseCode = "404",description = "Pedido não encontrado")
            }
    )
    public ResponseEntity<RequestResDTO> getRequest(@PathVariable UUID uuid){
        RequestResDTO response =getRequestUseCase.execute(uuid);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "/requests/{uuid}",
            description = "Atualizar pedido",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Pedido atualizado com sucesso"),
                    @ApiResponse(responseCode = "404",description = "Pedido não encontrado"),
                    @ApiResponse(responseCode = "400",description = "Erro na requisição")
            }
    )
    public ResponseEntity<MessageStatusDTO> updateRequest(@PathVariable UUID uuid,@RequestBody RequestReqDTO request){
        MessageStatusDTO response = updateRequestUseCase.execute(uuid,request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "/requests/{uuid}",
            description = "Deletar produto",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Pedido deletado com sucesso"),
                    @ApiResponse(responseCode = "404",description = "Pedido não encontrado")
            }
    )
    public ResponseEntity<Void> deleteRequest(@PathVariable UUID uuid){
        deleteClientUseCase.execute(uuid);

        return ResponseEntity.noContent().build();
    }
}
