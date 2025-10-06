package com.assignment.inventory.inventory_management_system.config;

// manually creating bean for ModelMapper via @Configuration and @Bean annotations

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapperObject(){
        return new ModelMapper();
    }
}
