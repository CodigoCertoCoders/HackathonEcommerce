package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ProductResponseDTO;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetProductByUuidUseCase {
    @Autowired
    private ProductService service;

    public ProductResponseDTO execute(UUID uuid){
        boolean existsProductById = service.existsByUUID(uuid);
        if(!existsProductById){
            throw new HandleNotFoundException("Produto");
        }
        return service.getProductById(uuid);
    }
}
