package com.epam.mentoring.kubernetes.postservice.controller.requestfailure;

import com.epam.mentoring.kubernetes.postservice.dto.response.PostResponseDto;

/**
 * @author Eduard_Sardyka
 */
public class PostRequestFailure extends PostResponseDto {

    private String errorMessage;

    public PostRequestFailure(Long id, Long authorId, String text, String postedAt, String errorMessage) {
        super(id, authorId, text, postedAt);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}