package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.RequestResDTO;
import com.guardians_of_the_code.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetRequestUseCase {
    @Autowired
    private RequestService requestService;

    public RequestResDTO execute(UUID uuid){
        return requestService.findRequest(uuid);
    }
}
