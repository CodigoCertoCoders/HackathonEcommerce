package com.guardians_of_the_code.services;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.dtos.RequestResDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.interfaces.RequestInterface;
import com.guardians_of_the_code.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestService implements RequestInterface {
    @Autowired
    private RequestRepository repository;

    @Override
    public List<RequestResDTO> findRequestByClient(List<UUID> clientUuids) {
        return repository.findRequestByClient(clientUuids);
    }

    @Override
    public RequestResDTO findRequest(UUID uuid) {
        return repository.findRequest(uuid);
    }

    @Override
    public Request saveRequest(RequestReqDTO request) {
        return repository.saveRequest(request);
    }

    @Override
    public MessageStatusDTO updateRequest(UUID uuid, RequestReqDTO request) {
        return repository.updateRequest(uuid,request);
    }

    @Override
    public void deleteRequest(UUID uuid) {
        repository.deleteRequest(uuid);
    }

    @Override
    public boolean existsRequest(UUID uuid) {
        return repository.existsRequest(uuid);
    }
}
