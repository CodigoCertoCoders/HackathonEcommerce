package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.ProductRequestDTO;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateProductUseCase {
    @Autowired
    private ProductService service;

    public MessageStatusDTO execute(UUID uuid, ProductRequestDTO request){
        boolean existsProduct = service.existsByUUID(uuid);

        if(!existsProduct){
            throw new HandleNotFoundException("Produto");
        }

        return service.updateProduct(uuid,request.getName(), request.getDescription(), request.getCategory(), request.getPrice(), request.getImage());
    }
}
