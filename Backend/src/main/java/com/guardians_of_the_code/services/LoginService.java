package com.guardians_of_the_code.services;

import com.guardians_of_the_code.dtos.ClientResponseDTO;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.interfaces.JPAInterfaceClient;
import com.guardians_of_the_code.interfaces.LoginInterface;
import com.guardians_of_the_code.repositories.LoginRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements LoginInterface {
    @Autowired
    private LoginRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JPAInterfaceClient jpaInterfaceClient;

    @Override
    public boolean login(String email, String password) {
        return repository.login(email,password);
    }

    public ClientResponseDTO findClientByEmail(String email){
        Optional<Client> client = jpaInterfaceClient.findClientByEmail(email);
        if(client.isEmpty()){
            throw new HandleNotFoundException("Cliente");
        }
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    public String createToken(){
        return repository.createToken();
    }
}
