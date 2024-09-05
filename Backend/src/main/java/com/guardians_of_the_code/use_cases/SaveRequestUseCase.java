package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveRequestUseCase {
    @Autowired
    private RequestService service;

    public Request execute(RequestReqDTO request){

        return service.saveRequest(request);
    }
}
