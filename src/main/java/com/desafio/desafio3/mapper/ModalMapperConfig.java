package com.desafio.desafio3.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModalMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setCollectionsMergeEnabled(true)
                .setDeepCopyEnabled(true);

        modelMapper.getConfiguration()
                .setPropertyCondition(context -> context.getSource() != null);

        return modelMapper;
    }
}
