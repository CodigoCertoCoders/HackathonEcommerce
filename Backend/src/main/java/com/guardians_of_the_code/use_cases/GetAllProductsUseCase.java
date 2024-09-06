package com.guardians_of_the_code.use_cases;

import com.guardians_of_the_code.dtos.GetAllProductsResponseDTO;
import com.guardians_of_the_code.dtos.ProductResponseDTO;
import com.guardians_of_the_code.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllProductsUseCase {
    @Autowired
    private ProductService service;

    public GetAllProductsResponseDTO execute(){
        List<ProductResponseDTO> products = service.getAllProducts();

        String path = "http://localhost:8080/";

        return new GetAllProductsResponseDTO(products,path);
    }
}
