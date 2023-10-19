package com.epam.mentoring.kubernetes.postservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.epam.mentoring.kubernetes.postservice.controller.PostController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Eduard_Sardyka
 */
@EnableWebMvc
@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = {
    com.epam.mentoring.kubernetes.postservice.repository.PostRepository.class })
@ComponentScan(basePackageClasses = {
    com.epam.mentoring.kubernetes.postservice.repository.PostRepository.class,
    PostController.class,
    com.epam.mentoring.kubernetes.postservice.configuration.Config.class,
    com.epam.mentoring.kubernetes.postservice.service.PostService.class,
    com.epam.mentoring.kubernetes.postservice.service.UserServiceInvocation.class})
@EntityScan(basePackageClasses = {
    com.epam.mentoring.kubernetes.postservice.domain.Post.class })
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}