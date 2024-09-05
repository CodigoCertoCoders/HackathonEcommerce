package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.dtos.GetAllProductsResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.ProductResponseDTO;
import com.guardians_of_the_code.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductInterface {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(UUID uuid);
    Product createProduct(String name, String description, String category, Double price, MultipartFile image);
    MessageStatusDTO updateProduct(UUID uuid,String name, String description, String category, Double price, MultipartFile image);
    void deleteProduct(UUID uuid);
    boolean existsByUUID(UUID uuid);

}
