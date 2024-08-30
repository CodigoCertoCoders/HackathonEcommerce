package com.guardians_of_the_code.exceptions;

import com.guardians_of_the_code.dtos.ClientErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleGlobalExceptions {
    @ExceptionHandler(HandleNotFoundException.class)
    public ResponseEntity<ClientErrorDTO> handleNotFound(HandleNotFoundException ex){
        ClientErrorDTO errorResponse = new ClientErrorDTO("Registro não encontrado",ex.getMessage(),404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleBadRequestException.class)
    public ResponseEntity<ClientErrorDTO> handleBadRequest(HandleBadRequestException ex){
        ClientErrorDTO errorResponse = new ClientErrorDTO("Erro na requisição",ex.getMessage(),422);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
