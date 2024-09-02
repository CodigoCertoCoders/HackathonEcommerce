package com.guardians_of_the_code.repositories;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.interfaces.JPAInterfaceClient;
import com.guardians_of_the_code.interfaces.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class LoginRepository implements LoginInterface {
    @Autowired
    private JPAInterfaceClient jpaRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean login(String email, String password) {
        Optional<Client> client = jpaRepository.findClientByEmail(email);
        if(client.isEmpty()){
            throw new HandleNotFoundException("Cliente");
        }
        return encoder.matches(password,client.get().getPassword());
    }

    @Override
    public String createToken() {
        try {
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + 3600 * 1000);
            Algorithm algorithm = Algorithm.HMAC256("GuardioesDoCodigoMaltex");
            return JWT.create()
                    .withIssuer("MalteX")
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Falha ao criar token");
        }
    }
}
