package com.epam.mentoring.kubernetes.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Eduard_Sardyka
 */
@EnableWebMvc
@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = {
    com.epam.mentoring.kubernetes.userservice.repository.UserRepository.class })
@ComponentScan(basePackageClasses = {
    com.epam.mentoring.kubernetes.userservice.repository.UserRepository.class,
    com.epam.mentoring.kubernetes.userservice.controller.UserController.class,
    com.epam.mentoring.kubernetes.userservice.configuration.Config.class,
    com.epam.mentoring.kubernetes.userservice.service.UserService.class })
@EntityScan(basePackageClasses = {
    com.epam.mentoring.kubernetes.userservice.domain.User.class })
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}