package com.guardians_of_the_code.exceptions;

import com.guardians_of_the_code.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleGlobalExceptions {
    @ExceptionHandler(HandleNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(HandleNotFoundException ex){
        ErrorDTO errorResponse = new ErrorDTO("Registro não encontrado",ex.getMessage(),404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleBadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequest(HandleBadRequestException ex){
        ErrorDTO errorResponse = new ErrorDTO("Erro na requisição",ex.getMessage(),422);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HandleConflictException.class)
    public ResponseEntity<ErrorDTO> handleConflict(HandleConflictException ex){
        ErrorDTO errorResponse = new ErrorDTO("Conflito no banco de dados",ex.getMessage(),409);
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HandleProductsNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductotFound(HandleProductsNotFoundException ex){
        ErrorDTO errorResponse = new ErrorDTO("Registros não encontrados",ex.getMessage(),404);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
