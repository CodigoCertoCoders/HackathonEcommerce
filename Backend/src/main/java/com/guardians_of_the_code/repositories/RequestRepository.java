package com.guardians_of_the_code.repositories;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.dtos.RequestResDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.interfaces.JPAInterfaceRequest;
import com.guardians_of_the_code.interfaces.RequestInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RequestRepository implements RequestInterface {
    @Autowired
    private JPAInterfaceRequest jpaInterfaceRequest;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RequestResDTO> findRequestByClient(List<UUID> clientUuids) {
        List<Request> requests = jpaInterfaceRequest.findByClientsIdInRequest(clientUuids);
        if(requests.isEmpty()){
            throw new HandleNotFoundException("Pedidos");
        }
        List<RequestResDTO> requestResDTOSList = new ArrayList<>();
        for (Request request:requests){
            RequestResDTO requestRes = modelMapper.map(request,RequestResDTO.class);
            requestResDTOSList.add(requestRes);
        }

        return requestResDTOSList;
    }

    @Override
    public RequestResDTO findRequest(UUID uuid) {
        Optional<Request> request = jpaInterfaceRequest.findById(uuid);
        if(request.isEmpty()){
            throw new HandleNotFoundException("Pedido");
        }
        return modelMapper.map(request,RequestResDTO.class);
    }

    @Override
    public Request saveRequest(RequestReqDTO request) {
        Request requestModel = modelMapper.map(request,Request.class);
        jpaInterfaceRequest.save(requestModel);
        return requestModel;
    }

    @Override
    public MessageStatusDTO updateRequest(UUID uuid, RequestReqDTO request) {
        Optional<Request> requestModel = jpaInterfaceRequest.findById(uuid);
        if(requestModel.isEmpty()){
            throw new HandleNotFoundException("Pedido");
        }
        Request existingRequest = requestModel.get();

        if(request.getPrice() != 0 || request.getPrice() > 0){
            //existingRequest
        }

        if(request.getClient_id() != null){
            //existingRequest
        }

        jpaInterfaceRequest.save(existingRequest);
        return new MessageStatusDTO("Pedido atualizado",200);
    }

    @Override
    public void deleteRequest(UUID uuid) {
        jpaInterfaceRequest.deleteById(uuid);
    }

    @Override
    public boolean existsRequest(UUID uuid) {
        return jpaInterfaceRequest.existsById(uuid);
    }
}
