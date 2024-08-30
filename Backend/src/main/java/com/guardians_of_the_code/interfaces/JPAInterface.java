package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JPAInterface extends JpaRepository<Client,UUID> {
    boolean existsClientByEmail(String email);

    Optional<Client> findClientById(UUID id);

    boolean existsClientById(UUID uuid);
}

