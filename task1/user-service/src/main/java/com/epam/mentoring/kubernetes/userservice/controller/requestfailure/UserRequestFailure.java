package com.epam.mentoring.kubernetes.userservice.controller.requestfailure;

import com.epam.mentoring.kubernetes.userservice.dto.response.UserResponseDto;

/**
 * @author Eduard_Sardyka
 */
public class UserRequestFailure extends UserResponseDto {

    private String errorMessage;

    public UserRequestFailure(Long id, String username, Long amountOfPosts, String errorMessage) {
        super(id, username, amountOfPosts);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}