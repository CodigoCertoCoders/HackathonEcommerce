package com.guardians_of_the_code.services;

import com.guardians_of_the_code.dtos.GetAllProductsResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.ProductResponseDTO;
import com.guardians_of_the_code.entities.Product;
import com.guardians_of_the_code.interfaces.ProductInterface;
import com.guardians_of_the_code.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements ProductInterface {
    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public ProductResponseDTO getProductById(UUID uuid) {
        return repository.getProductById(uuid);
    }

    @Override
    public Product createProduct(String name, String description, String category, Double price, MultipartFile image) {
        return repository.createProduct(name, description, category, price, image);
    }

    @Override
    public MessageStatusDTO updateProduct(UUID uuid,String name, String description, String category, Double price, MultipartFile image) {
        return repository.updateProduct(uuid,name, description, category, price, image);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        repository.deleteProduct(uuid);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return repository.existsByUUID(uuid);
    }
}
