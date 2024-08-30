package com.guardians_of_the_code.exceptions;

public class HandleBadRequestException extends RuntimeException{
    public HandleBadRequestException(String message){
        super(message);
    }
}
