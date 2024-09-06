package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.LoginRequestDTO;
import com.guardians_of_the_code.dtos.LoginResponseDTO;
import com.guardians_of_the_code.use_cases.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginUseCase useCase;

    @PostMapping("/verifyCredentials")
    @Operation(
            summary = "/login/verifyCredentials",
            description = "Realizar login",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Cliente logado com sucesso"),
                    @ApiResponse(responseCode = "401",description = "Cliente não autorizado"),
                    @ApiResponse(responseCode = "403",description = "Cliente sem acesso"),
                    @ApiResponse(responseCode = "400",description = "Erro nas credenciais")
            }
    )
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO login = useCase.execute(request);

        return ResponseEntity.ok(login);
    }
}
