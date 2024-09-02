package com.guardians_of_the_code.services;

import com.guardians_of_the_code.interfaces.LoginInterface;
import com.guardians_of_the_code.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginInterface {
    @Autowired
    private LoginRepository repository;

    @Override
    public boolean login(String email, String password) {
        return repository.login(email,password);
    }

    public String createToken(){
        return repository.createToken();
    }
}
