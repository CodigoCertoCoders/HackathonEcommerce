package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.LoginRequestDTO;
import com.guardians_of_the_code.dtos.LoginResponseDTO;
import com.guardians_of_the_code.use_cases.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginUseCase useCase;

    @PostMapping("/verifyCredentials")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO login = useCase.execute(request);

        return ResponseEntity.ok(login);
    }
}
