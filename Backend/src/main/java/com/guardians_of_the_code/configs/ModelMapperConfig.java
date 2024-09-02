package com.guardians_of_the_code.configs;

import com.guardians_of_the_code.dtos.ProductResponseDTO;
import com.guardians_of_the_code.entities.Product;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Product, ProductResponseDTO>() {
            @Override
            protected void configure() {
                map().setUuid(source.getId());
                map().setName(source.getName());
                map().setUrl(source.getUrl());
                map().setCategory(source.getCategory());
                map().setPrice(source.getPrice());
                map().setDescription(source.getDescription());
            }
        });

        return modelMapper;
    }
}
