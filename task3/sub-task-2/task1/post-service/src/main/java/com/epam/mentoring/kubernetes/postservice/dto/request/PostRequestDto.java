package com.epam.mentoring.kubernetes.postservice.dto.request;

/**
 * @author Eduard_Sardyka
 */
public class PostRequestDto {

    private Long id;

    private Long authorId;

    private String text;

    public PostRequestDto() {
    }

    public PostRequestDto(Long id, Long authorId, String text) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}