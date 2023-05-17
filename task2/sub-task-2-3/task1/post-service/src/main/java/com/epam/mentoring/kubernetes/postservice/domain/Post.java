package com.epam.mentoring.kubernetes.postservice.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduard_Sardyka
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "text", length = 100)
    private String text;

    @Column(name = "posted_at")
    private LocalDate postedAt;

    protected Post() {
    }

    public Post(Long id, Long authorId, String text, LocalDate postedAt) {
        this.id = id;
        this.authorId = authorId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDate postedAt) {
        this.postedAt = postedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(authorId, post.authorId)
            && Objects.equals(text, post.text) && Objects.equals(postedAt, post.postedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, text, postedAt);
    }
}