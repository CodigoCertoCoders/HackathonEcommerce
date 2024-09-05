package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ProductIdDTO;
import com.guardians_of_the_code.dtos.RequestReqDTO;
import com.guardians_of_the_code.entities.Request;
import com.guardians_of_the_code.exceptions.HandleBadRequestException;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ClientService;
import com.guardians_of_the_code.services.ProductService;
import com.guardians_of_the_code.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SaveRequestUseCase {
    @Autowired
    private RequestService service;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    public Request execute(RequestReqDTO request) {
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

        // Valida e converte a lista de produtos para JSON
        List<String> productUUIDStrings = request.getProducts();

        for(String product: productUUIDStrings){
            boolean existsProductByUUID=productService.existsByUUID(UUID.fromString(product));
            if(!existsProductByUUID){
                throw new HandleNotFoundException("Produto");
            }
        }

        // Cria a solicitação com a lista de UUIDs como Strings
        request.setProducts(productUUIDStrings);

        // Chama o serviço para salvar a solicitação




        return service.saveRequest(request);

    }

}
