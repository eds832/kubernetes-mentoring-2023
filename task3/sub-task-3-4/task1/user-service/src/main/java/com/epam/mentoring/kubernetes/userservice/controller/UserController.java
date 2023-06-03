package com.epam.mentoring.kubernetes.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mentoring.kubernetes.userservice.controller.requestfailure.UserRequestFailure;
import com.epam.mentoring.kubernetes.userservice.domain.User;
import com.epam.mentoring.kubernetes.userservice.dto.request.UserRequestDto;
import com.epam.mentoring.kubernetes.userservice.dto.response.UserResponseDto;
import com.epam.mentoring.kubernetes.userservice.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Eduard_Sardyka
 */
@RestController
@RequestMapping(path = "/users")
@Api(value = "user-service")
public class UserController {

    private UserService userService;

    private ConversionService conversionService;

    @Autowired
    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = UserResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Username of user to be created.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = UserResponseDto.class),
        @ApiResponse(code = 400, message = "Validation error or request body is an invalid."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        if (userRequestDto.getUsername() != null && !userRequestDto.getUsername().isBlank()) {
            userRequestDto.setId(null);
            userRequestDto.setAmountOfPosts(0L);
            User user = conversionService.convert(userRequestDto, User.class);
            user = userService.createUser(user);
            return convertUserToResponseEntity(user);
        } else {
            return ResponseEntity.badRequest()
                .body(new UserRequestFailure(null, userRequestDto.getUsername(),
                    0L, "Validation error or request body is an invalid."));
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = UserResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Update user’s data.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = UserResponseDto.class),
        @ApiResponse(code = 400, message = "Validation error or request body is an invalid."),
        @ApiResponse(code = 404, message = "User doesn’t exist with given id."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<UserResponseDto> updateUser(
        @ApiParam("Id of user to update.") @PathVariable(value = "id") Long id,
        @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(id);
        if (userRequestDto.getId() != null && userRequestDto.getUsername() != null && userRequestDto.getAmountOfPosts() != null) {
            User user = conversionService.convert(userRequestDto, User.class);
            user = userService.updateUser(user);
            if (user != null) {
                return convertUserToResponseEntity(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest()
                .body(new UserRequestFailure(userRequestDto.getId(), userRequestDto.getUsername(),
                    userRequestDto.getAmountOfPosts(),"Validation error or request body is an invalid."));
        }
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = UserResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Delete a user by id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = UserResponseDto.class),
        @ApiResponse(code = 404, message = "Not Found."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<UserResponseDto> deleteUser(
        @ApiParam("Id of the User to be deleted. Cannot be empty.") @PathVariable(value = "id") Long id) {
        if (id != null) {
            User user = userService.deleteUser(id);
            if (user != null) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = UserResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Gets user’s data by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = UserResponseDto.class),
        @ApiResponse(code = 404, message = "User doesn’t exist with given id."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<UserResponseDto> getUser(
        @ApiParam("Id of user to delete.") @PathVariable(value = "id") Long id) {
        if (id != null) {
            User user = userService.getUser(id);
            if (user != null) {
                return convertUserToResponseEntity(user);
            }
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<UserResponseDto> convertUserToResponseEntity(User user) {
        UserResponseDto dto = conversionService.convert(user, UserResponseDto.class);
        return ResponseEntity.ok(dto);
    }

}