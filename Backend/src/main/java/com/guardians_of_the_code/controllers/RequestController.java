package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.use_cases.SaveRequestUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private SaveRequestUseCase saveRequestUseCase;

    public ResponseEntity<MessageStatusDTO> saveRequest(@RequestBody RequestReqDTO request, UriComponentsBuilder uriBuilder){
        Request requestModel = saveRequestUseCase.execute(request);
        System.out.println(requestModel);
        URI path = uriBuilder.buildAndExpand("/requests/"+requestModel).toUri();
        MessageStatusDTO response = new MessageStatusDTO("pedido salvo",201);
        return ResponseEntity.created(path).body(response);
    }
}
