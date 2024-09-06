package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ClientService;
import com.guardians_of_the_code.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UpdateRequestUseCase {
    @Autowired
    private RequestService service;

    @Autowired
    private ClientService clientService;

    public MessageStatusDTO execute(UUID uuid, RequestReqDTO request){
        if (request.getFreight() <= 0) {
            throw new HandleBadRequestException("Frete não pode ser menor ou igual a 0");
        }

        if (request.getPrice() <= 0) {
            throw new HandleBadRequestException("Total não pode ser menor ou igual a 0");
        }

        boolean clientExists = clientService.existsClient(request.getClient_id().getId());

        if (!clientExists) {
            throw new HandleNotFoundException("Cliente");
        }

        if (request.getQuantity() <= 0) {
            throw new HandleBadRequestException("Quantidade não pode ser menor ou igual a 0");
        }

        if (request.getProducts() == null || request.getProducts().isEmpty()) {
            throw new HandleBadRequestException("Tem que ter pelo menos 1 produto para realizar o pedido");
        }

        List<String> productsName = request.getProducts();

        request.setProducts(productsName);

        return service.updateRequest(uuid,request);
    }
}
