package com.epam.mentoring.kubernetes.postservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private RestTemplate restTemplate;

    private ConversionService conversionService;

    @Autowired
    public UserServiceInvocation(RestTemplate restTemplate, ConversionService conversionService) {
        this.restTemplate = restTemplate;
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
            userDto = restTemplate.getForObject(URL + id, UserResponseDto.class);
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<UserRequestDto> requestEntity = new HttpEntity<UserRequestDto>(userRequestDto, headers);
        UserResponseDto userResponseDto = null;
        try {
            ResponseEntity<UserResponseDto> response = restTemplate.exchange(URL + userRequestDto.getId(), HttpMethod.PUT,
                requestEntity, UserResponseDto.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                userResponseDto = response.getBody();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Problem with updating User: " + user + ", error message: " + e.getMessage());
        }
        return userResponseDto;
    }
}