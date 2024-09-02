package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.*;
import com.guardians_of_the_code.entities.Product;
import com.guardians_of_the_code.use_cases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @Autowired
    private GetAllProductsUseCase getAllProductsUseCase;

    @Autowired
    private GetProductByUuidUseCase getProductByUuidUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @Autowired
    private DeleteProductUseCase deleteProductUseCase;

    @GetMapping
    public ResponseEntity<GetAllProductsResponseDTO> getAllProducts(){
        GetAllProductsResponseDTO products = getAllProductsUseCase.execute();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductResponseDTO> getProductByUuid(@PathVariable UUID uuid){
        ProductResponseDTO product = getProductByUuidUseCase.execute(uuid);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<MessageStatusDTO> createProduct(@ModelAttribute ProductRequestDTO product, UriComponentsBuilder builder) {
        Product productModel = createProductUseCase.execute(product);
        URI path = builder.buildAndExpand("/products/"+ productModel.getId()).toUri();

        MessageStatusDTO response = new MessageStatusDTO("Produto criado com sucesso",201);

        return ResponseEntity.created(path).body(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<MessageStatusDTO> updateProduct(@PathVariable UUID uuid,@ModelAttribute ProductRequestDTO product){
        MessageStatusDTO response = updateProductUseCase.execute(uuid,product);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid){
        deleteProductUseCase.execute(uuid);

        return ResponseEntity.noContent().build();
    }
}
