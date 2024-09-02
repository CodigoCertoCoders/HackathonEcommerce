package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.GetAllProductsResponseDTO;
import com.guardians_of_the_code.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllProductsUseCase {
    @Autowired
    private ProductService service;

    public GetAllProductsResponseDTO execute(){
        return service.getAllProducts();
    }
}
