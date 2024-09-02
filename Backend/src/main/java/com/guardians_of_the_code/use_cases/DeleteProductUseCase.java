package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteProductUseCase {
    @Autowired
    private ProductService service;

    public void execute(UUID uuid){
        boolean existsProduct = service.existsByUUID(uuid);
        if(!existsProduct){
            throw new HandleNotFoundException("Produto");
        }

        service.deleteProduct(uuid);
    }
}
