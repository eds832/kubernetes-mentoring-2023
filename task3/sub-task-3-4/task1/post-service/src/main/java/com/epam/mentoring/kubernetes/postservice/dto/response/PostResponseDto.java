package com.epam.mentoring.kubernetes.postservice.dto.response;

/**
 * @author Eduard_Sardyka
 */
public class PostResponseDto {

    private Long id;

    private Long authorId;

    private String topic;

    private String text;

    private String postedAt;

    public PostResponseDto(Long id, Long authorId, String topic, String text, String postedAt) {
        this.id = id;
        this.authorId = authorId;
        this.topic = topic;
        this.text = text;
        this.postedAt = postedAt;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}