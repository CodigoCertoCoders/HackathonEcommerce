package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.dtos.RequestResDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.use_cases.GetRequestUseCase;
import com.guardians_of_the_code.use_cases.SaveRequestUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private SaveRequestUseCase saveRequestUseCase;

    @Autowired
    private GetRequestUseCase getRequestUseCase;

    @PostMapping
    public ResponseEntity<MessageStatusDTO> saveRequest(@RequestBody RequestReqDTO request, UriComponentsBuilder uriBuilder){
        Request requestModel = saveRequestUseCase.execute(request);
        URI path = uriBuilder.buildAndExpand("/requests/"+requestModel).toUri();
        MessageStatusDTO response = new MessageStatusDTO("pedido salvo",201);

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping("/getAll/{uuid}")
    public ResponseEntity<RequestResDTO> getRequest(@PathVariable UUID uuid){
        RequestResDTO response =getRequestUseCase.execute(uuid);

        return ResponseEntity.ok(response);
    }
}
