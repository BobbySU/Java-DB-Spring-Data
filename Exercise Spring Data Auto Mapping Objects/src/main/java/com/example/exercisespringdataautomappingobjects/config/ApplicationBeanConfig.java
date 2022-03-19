package com.example.exercisespringdataautomappingobjects.config;

import com.example.exercisespringdataautomappingobjects.model.dto.GameAddDTO;
import com.example.exercisespringdataautomappingobjects.model.entity.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
            modelMapper.typeMap(GameAddDTO.class, Game.class).addMappings(mapper ->
                    mapper.map(GameAddDTO::getImageThumbnailURL, Game::setImageThumbnail));

//        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
//                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            }
//        };
//
//        modelMapper.addConverter(localDateConverter);

        return modelMapper;
    }
}
