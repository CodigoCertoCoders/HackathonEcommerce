package com.guardians_of_the_code.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.guardians_of_the_code.dtos.*;
import com.guardians_of_the_code.entities.Client;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.interfaces.JPAInterfaceRequest;
import com.guardians_of_the_code.interfaces.RequestInterface;
import com.guardians_of_the_code.services.ClientService;
import com.guardians_of_the_code.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RequestRepository implements RequestInterface {
    @Autowired
    private JPAInterfaceRequest jpaInterfaceRequest;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

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

    public Request saveRequest(RequestReqDTO request) {
        Request requestModel = new Request();
        requestModel.setQuantity(request.getQuantity());
        requestModel.setPrice(request.getPrice());
        requestModel.setFreight(request.getFreight());

        ClientResponseDTO clientResponseDTO=service.findByClient(request.getClient_id().getId());
        Client client=modelMapper.map(clientResponseDTO,Client.class);
        requestModel.setClient(client);

        System.out.println("prodcts  request"+request.getProducts());

        // Converte a lista de IDs para JSON
        String productsJson;
        try {
            productsJson = objectMapper.writeValueAsString(request.getProducts());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter a lista de produtos para JSON", e);
        }

        // Define o JSON no campo products do requestModel
        requestModel.setProducts(productsJson);

        // Salva a entidade Request no banco de dados
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

        if(request.getPrice() > 0){
            existingRequest.setPrice(request.getPrice());
        }

        if(request.getClient_id() != null){
            ClientResponseDTO clientDTO = service.findByClient(request.getClient_id().getId());
            Client client = modelMapper.map(clientDTO,Client.class);

            existingRequest.setClient(client);
        }

        if(request.getQuantity() > 0){
            existingRequest.setQuantity(request.getQuantity());
        }

        if(request.getFreight() > 0){
            existingRequest.setFreight(request.getFreight());
        }

        if(!request.getProducts().isEmpty()){
            //List<String> productsList = new ArrayList<>(request.getProducts());

            //existingRequest.setProducts(productsList);
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
