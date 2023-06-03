package com.epam.mentoring.kubernetes.userservice.domain;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "amount_of_posts")
    private Long amountOfPosts;

    protected User() {
    }

    public User(Long id, String username, Long amountOfPosts) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
            && Objects.equals(amountOfPosts, user.amountOfPosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, amountOfPosts);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", amountOfPosts=" + amountOfPosts +
            '}';
    }
}