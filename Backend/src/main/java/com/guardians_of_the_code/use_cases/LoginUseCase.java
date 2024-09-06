package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.dtos.LoginRequestDTO;
import com.guardians_of_the_code.dtos.LoginResponseDTO;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ClientService;
import com.guardians_of_the_code.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCase {
    @Autowired
    private LoginService service;

    @Autowired
    private ClientService clientService;

    public LoginResponseDTO execute(LoginRequestDTO request){
        if(request.getEmail().isEmpty() || request.getEmail().isBlank()){
            throw new HandleBadRequestException("Email não pode ser vazio ou nulo");
        }

        if(request.getEmail().length() > 120 || request.getEmail().length() < 15){
            throw new HandleBadRequestException("O email deve ter no minimo 15 e no máximo 120 caracteres");
        }

        if(request.getPassword().isEmpty() || request.getPassword().isBlank()){
            throw new HandleBadRequestException("Senha não pode ser vazia ou nula");
        }

        if(request.getPassword().length() > 100 || request.getPassword().length() < 8){
            throw new HandleBadRequestException("O senha deve ter no minimo 8 e no máximo 100 caracteres");
        }

       boolean isLogin = service.login(request.getEmail(), request.getPassword());

        if(!isLogin){
            throw new HandleNotFoundException("Email/e Senha");
        }

        String token = service.createToken();
        if(token == null) {
            throw new RuntimeException("Não foi possivel criar token");
        }

        boolean updateToken = clientService.updateTokenClient(request.getEmail(), token);
        if(!updateToken){
            throw new RuntimeException("Falha ao tentar atualizar token");
        }

        ClientResponseDTO clientResponseDTO = service.findClientByEmail(request.getEmail());

        return new LoginResponseDTO("Login realizado com sucesso", 200, token,clientResponseDTO);
    }
}
