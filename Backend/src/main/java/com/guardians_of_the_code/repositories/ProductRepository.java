package com.guardians_of_the_code.repositories;

import com.guardians_of_the_code.dtos.GetAllProductsResponseDTO;
import com.guardians_of_the_code.dtos.MessageStatusDTO;
import com.guardians_of_the_code.dtos.ProductResponseDTO;
import com.guardians_of_the_code.entities.Product;
import com.guardians_of_the_code.enums.Category;
import com.guardians_of_the_code.exceptions.HandleNotFoundException;
import com.guardians_of_the_code.exceptions.HandleProductsNotFoundException;
import com.guardians_of_the_code.interfaces.JPAInterfaceProduct;
import com.guardians_of_the_code.interfaces.ProductInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepository implements ProductInterface {
    @Autowired
    private JPAInterfaceProduct jpaInterfaceProduct;

    @Autowired
    private ModelMapper modelMapper;

    private final Path fileStorageLocation;

    public ProductRepository() {
        this.fileStorageLocation = Paths.get("src/main/java/com/guardians_of_the_code/assets").toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new RuntimeException("Não foi possível criar o diretório para armazenar as fotos dos produtos: ", ex);
        }
    }

    @Override
    public GetAllProductsResponseDTO getAllProducts() {
        List<Product> products = jpaInterfaceProduct.findAll();
        if(products.isEmpty()){
            throw new HandleProductsNotFoundException("Produtos não encontrados");
        }
        List<ProductResponseDTO> productResponseDTOS = products.stream()
                .map(product -> {
                    ProductResponseDTO dto = new ProductResponseDTO();
                    dto.setUuid(product.getId());
                    dto.setName(product.getName());
                    dto.setUrl(product.getUrl());
                    dto.setCategory(product.getCategory());
                    dto.setPrice(product.getPrice());
                    dto.setDescription(product.getDescription());
                    return dto;
                })
                .toList();
        return new GetAllProductsResponseDTO(productResponseDTOS);
    }

    @Override
    public ProductResponseDTO getProductById(UUID uuid) {
        Optional<Product> product = jpaInterfaceProduct.findById(uuid);

        Product productResponse = product.get();

        return modelMapper.map(productResponse, ProductResponseDTO.class);
    }

    @Override
    public MessageStatusDTO createProduct(String name, String description, String category, Double price, MultipartFile image) {
        Category categoryEnum = null;

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);

        categoryEnum = switch (category) {
            case "Cerveja" -> Category.CERVEJA;
            case "Whisky" -> Category.WHISKY;
            case "Vodka" -> Category.VODKA;
            case "Gin" -> Category.GIN;
            case "Energetico" -> Category.ENERGETICO;
            case "Vinho" -> Category.VINHO;
            case "Conhaque" -> Category.CONHAQUE;
            default -> throw new HandleNotFoundException("Categoria");
        };
        product.setCategory(categoryEnum);
        product.setPrice(price);

        if (image != null && !image.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            String productDirectory = product.getName();

            try {
                Path productDirectoryPath = this.fileStorageLocation.resolve(productDirectory);
                if (!Files.exists(productDirectoryPath)) {
                    Files.createDirectories(productDirectoryPath);
                }

                Path targetLocation = productDirectoryPath.resolve(filename);
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                product.setUrl(productDirectory + "/" + filename);

            } catch (Exception ex) {
                throw new RuntimeException("Não foi possível armazenar o arquivo. Por favor, tente novamente: " + ex.getMessage(), ex);
            }
        }

        jpaInterfaceProduct.save(product);
        return new MessageStatusDTO("Produto criado com sucesso", 201);
    }

    @Override
    public MessageStatusDTO updateProduct(UUID uuid,String name, String description, String category, Double price, MultipartFile image) {
        Optional<Product> productModel = jpaInterfaceProduct.findById(uuid);
        if(productModel.isEmpty()){
            throw new HandleNotFoundException("Produto");
        }

        Product existingProduct = productModel.get();
        if(name != null && !name.isEmpty()){
            String oldPath = "src/main/java/com/guardians_of_the_code/assets/"+existingProduct.getName();
            String newPath = "src/main/java/com/guardians_of_the_code/assets/"+name;
            File currentDirectory = new File(oldPath);
            File newDirectory = new File(newPath);

            if(currentDirectory.exists() && currentDirectory.isDirectory()){
                boolean success = currentDirectory.renameTo(newDirectory);
                if (success) {
                    System.out.println("Diretório renomeado com sucesso.");
                } else {
                    throw new RuntimeException("Falha ao renomear o diretório.");
                }
            } else {
                throw new RuntimeException("O diretório atual não existe ou não é um diretório válido.");
            }

            existingProduct.setName(name);
        }

        if(description != null && !description.isEmpty()){
            existingProduct.setDescription(description);
        }

        if(category != null && !category.isEmpty()){
            existingProduct.setCategory(Category.valueOf(category));
        }

        if(price != null){
            existingProduct.setPrice(price);
        }

        if (image != null && !image.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            try {
                String oldPath = "src/main/java/com/guardians_of_the_code/assets/"+existingProduct.getName();
                String newPath = "src/main/java/com/guardians_of_the_code/assets/"+name;
                File currentDirectory = new File(oldPath);
                File newDirectory = new File(newPath);

                //deletar path primeiro
                if(currentDirectory.isDirectory()){
                    File[] files = currentDirectory.listFiles();
                    if(files != null){
                        for(File file:files){
                            if(file.isFile()){
                                file.delete();
                            }
                        }
                    }
                } else if (newDirectory.isDirectory()) {
                    File[] files = newDirectory.listFiles();
                    if(files != null){
                        for(File file:files){
                            if(file.isFile()){
                                file.delete();
                            }
                        }
                    }
                }

                //atualizar foto
                Path productDirectoryPath = this.fileStorageLocation.resolve(name);
                if (!Files.exists(productDirectoryPath)) {
                    Files.createDirectories(productDirectoryPath);
                }

                Path targetLocation = productDirectoryPath.resolve(filename);
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                existingProduct.setUrl(name + "/" + filename);

            } catch (Exception ex) {
                throw new RuntimeException("Não foi possível armazenar o arquivo. Por favor, tente novamente: " + ex.getMessage(), ex);
            }
        }

        jpaInterfaceProduct.save(existingProduct);
        return new MessageStatusDTO("Produto atualizado com sucesso",200);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        jpaInterfaceProduct.deleteById(uuid);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return jpaInterfaceProduct.existsById(uuid);
    }
}
