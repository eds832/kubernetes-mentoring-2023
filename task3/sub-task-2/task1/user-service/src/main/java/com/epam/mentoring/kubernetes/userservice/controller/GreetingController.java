package com.epam.mentoring.kubernetes.userservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Eduard_Sardyka
 */
@RestController
@RequestMapping
@Api(value = "greeting-service")
public class GreetingController {

    public GreetingController() {
    }

    @GetMapping(path = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = String.class, produces = MediaType.TEXT_PLAIN_VALUE, value = "Hello, k8s!")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = String.class),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello, k8s!");
    }

}