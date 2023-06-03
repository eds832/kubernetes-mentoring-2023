package com.epam.mentoring.kubernetes.userservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.epam.mentoring.kubernetes.userservice.converter.UserRequestDtoToUserConverter;
import com.epam.mentoring.kubernetes.userservice.converter.UserToUserResponseDtoConverter;

/**
 * @author Eduard_Sardyka
 */
@Configuration
public class Config {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new UserRequestDtoToUserConverter());
        service.addConverter(new UserToUserResponseDtoConverter());
        return service;
    }

}