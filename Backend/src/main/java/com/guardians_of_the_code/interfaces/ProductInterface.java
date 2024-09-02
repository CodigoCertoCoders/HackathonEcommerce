package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.dtos.GetAllProductsResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.ProductResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ProductInterface {
    GetAllProductsResponseDTO getAllProducts();
    ProductResponseDTO getProductById(UUID uuid);
    MessageStatusDTO createProduct(String name, String description, String category, Double price, MultipartFile image);
    MessageStatusDTO updateProduct(UUID uuid,String name, String description, String category, Double price, MultipartFile image);
    void deleteProduct(UUID uuid);
    boolean existsByUUID(UUID uuid);

}
