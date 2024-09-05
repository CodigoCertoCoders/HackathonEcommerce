package com.guardians_of_the_code.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
