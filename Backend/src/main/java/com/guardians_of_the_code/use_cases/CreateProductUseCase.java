package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.ProductRequestDTO;
import com.guardians_of_the_code.entities.Product;
import com.guardians_of_the_code.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProductUseCase {
    @Autowired
    private ProductService service;

    public Product execute(ProductRequestDTO product){
        return service.createProduct(
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getImage());
    }
}
