package com.guardians_of_the_code.exceptions;

public class HandleProductsNotFoundException extends RuntimeException{
    public HandleProductsNotFoundException(String message){
        super(message);
    }
}
