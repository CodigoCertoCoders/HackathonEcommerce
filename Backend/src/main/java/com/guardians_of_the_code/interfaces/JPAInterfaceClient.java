package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JPAInterfaceClient extends JpaRepository<Client,UUID> {
    // clients
    boolean existsClientByEmail(String email);

    Optional<Client> findClientById(UUID id);

    Optional<Client> findByEmail(String email);

    boolean existsClientById(UUID uuid);

    boolean existsByIdAndEmail(UUID uuid, String email);

    boolean existsByEmailAndIdNot(String email,UUID uuid);

    //login
    Optional<Client> findClientByEmail(String email);
}
