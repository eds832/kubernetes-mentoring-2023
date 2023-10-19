package com.epam.mentoring.kubernetes.userservice.dto.request;

/**
 * @author Eduard_Sardyka
 */
public class UserRequestDto {

    private Long id;

    private String username;

    private Long amountOfPosts;

    public UserRequestDto() {
    }

    public UserRequestDto(Long id, String username, Long amountOfPosts) {
        this.id = id;
        this.username = username;
        this.amountOfPosts = amountOfPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAmountOfPosts() {
        return amountOfPosts;
    }

    public void setAmountOfPosts(Long amountOfPosts) {
        this.amountOfPosts = amountOfPosts;
    }
}