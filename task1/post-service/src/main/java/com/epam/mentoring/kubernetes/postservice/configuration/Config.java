package com.epam.mentoring.kubernetes.postservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.client.RestTemplate;

import com.epam.mentoring.kubernetes.postservice.converter.PostRequestDtoToPostConverter;
import com.epam.mentoring.kubernetes.postservice.converter.PostToPostResponseDtoConverter;
import com.epam.mentoring.kubernetes.postservice.converter.UserResponseDtoToUserConverter;
import com.epam.mentoring.kubernetes.postservice.converter.UserToUserRequestDtoConverter;

/**
 * @author Eduard_Sardyka
 */
@Configuration
public class Config {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new PostRequestDtoToPostConverter());
        service.addConverter(new PostToPostResponseDtoConverter());
        service.addConverter(new UserResponseDtoToUserConverter());
        service.addConverter(new UserToUserRequestDtoConverter());
        return service;
    }
}