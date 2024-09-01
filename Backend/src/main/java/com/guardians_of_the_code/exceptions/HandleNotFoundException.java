package com.guardians_of_the_code.exceptions;

public class HandleNotFoundException extends RuntimeException{
    public HandleNotFoundException(String object){
        super(object + " não encontrado");
    }
}
