package com.guardians_of_the_code.controllers;

import com.guardians_of_the_code.dtos.*;
import com.guardians_of_the_code.entities.Product;
import com.guardians_of_the_code.use_cases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "/products/",
            description = "Buscar todos produtos",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Produtos encontrados"),
                    @ApiResponse(responseCode = "404",description = "Sem produtos para exibir"),
            }
    )
    public ResponseEntity<GetAllProductsResponseDTO> getAllProducts(){
        GetAllProductsResponseDTO products = getAllProductsUseCase.execute();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "/products/{uuid}",
            description = "Buscar produto por uuid",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Produto encontrado"),
                    @ApiResponse(responseCode = "404",description = "Produto não encontrado"),
            }
    )
    public ResponseEntity<ProductResponseDTO> getProductByUuid(@PathVariable UUID uuid){
        ProductResponseDTO product = getProductByUuidUseCase.execute(uuid);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Operation(
            summary = "/products/",
            description = "Criar produto",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Produto criado com sucesso"),
                    @ApiResponse(responseCode = "400",description = "Erro na requisição")
            }
    )
    public ResponseEntity<MessageStatusDTO> createProduct(@ModelAttribute ProductRequestDTO product, UriComponentsBuilder builder) {
        Product productModel = createProductUseCase.execute(product);
        URI path = builder.buildAndExpand("/products/"+ productModel.getId()).toUri();

        MessageStatusDTO response = new MessageStatusDTO("Produto criado com sucesso",201);

        return ResponseEntity.created(path).body(response);
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "/products/{uuid}",
            description = "Atualizar produto",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Produto atualiazdo com sucesso"),
                    @ApiResponse(responseCode = "404",description = "Produto não encontrado"),
                    @ApiResponse(responseCode = "400",description = "Erro na requisição")
            }
    )
    public ResponseEntity<MessageStatusDTO> updateProduct(@PathVariable UUID uuid,@ModelAttribute ProductRequestDTO product){
        MessageStatusDTO response = updateProductUseCase.execute(uuid,product);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "/products/{uuid}",
            description = "Deletar produto",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Produto deletado com sucesso"),
                    @ApiResponse(responseCode = "404",description = "Produto não encontrado")
            }
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid){
        deleteProductUseCase.execute(uuid);

        return ResponseEntity.noContent().build();
    }
}
