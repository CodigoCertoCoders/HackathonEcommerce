package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JPAInterfaceRequest extends JpaRepository<Request, UUID> {
    @Query(value = "SELECT * FROM tb_request WHERE :uuids IN client_id",nativeQuery = true)
    List<Request> findByClientsIdInRequest(@Param("uuids") List<UUID> uuids);

    boolean existsById(UUID uuid);
}
