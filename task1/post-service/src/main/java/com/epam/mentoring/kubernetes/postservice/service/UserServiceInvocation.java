package com.epam.mentoring.kubernetes.postservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.epam.mentoring.kubernetes.postservice.domain.User;
import com.epam.mentoring.kubernetes.postservice.dto.request.UserRequestDto;
import com.epam.mentoring.kubernetes.postservice.dto.response.UserResponseDto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * * @author Eduard_Sardyka
 */
@Service
public class UserServiceInvocation {

    private final Logger logger = Logger.getLogger(UserServiceInvocation.class.getName());
    @Value("${user.app.url:http://user-app:8081/users/}")
    private String URL;

    private ConversionService conversionService;

    @Autowired
    public UserServiceInvocation(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public User getUserById(Long id) {
        UserResponseDto userDto = getUser(id);
        if (userDto != null) {
            return conversionService.convert(userDto, User.class);
        }
        return null;
    }

    private UserResponseDto getUser(Long id) {
        UserResponseDto userDto = null;
        try {
            userDto = WebClient.create()
                .get()
                .uri(URL + id)
                .retrieve()
                .bodyToMono(UserResponseDto.class)
                .block();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Problem with getting User by id = " + id + ", error message: " + e.getMessage());
        }
        return userDto;
    }

    public User updateUserInfo(User user) {
        UserResponseDto userDto = updateUser(user);
        if (userDto != null) {
            return conversionService.convert(userDto, User.class);
        }
        return null;
    }

    private UserResponseDto updateUser(User user) {
        UserRequestDto userRequestDto = conversionService.convert(user, UserRequestDto.class);
        UserResponseDto userResponseDto = null;
        try {
            ResponseEntity<UserResponseDto> response = WebClient
                .create()
                .put()
                .uri(URL + user.getId())
                .bodyValue(userRequestDto)
                .retrieve()
                .toEntity(UserResponseDto.class)
                .block();
            if (response.getStatusCode() == HttpStatus.OK) {
                userResponseDto = response.getBody();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Problem with updating User: " + user + ", error message: " + e.getMessage());
        }
        return userResponseDto;
    }
}