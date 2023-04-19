package com.epam.mentoring.kubernetes.postservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.kubernetes.postservice.domain.User;
import com.epam.mentoring.kubernetes.postservice.repository.PostRepository;
import com.epam.mentoring.kubernetes.postservice.domain.Post;

/**
 * @author Eduard_Sardyka
 */
@Service
public class PostService {

    private PostRepository postRepository;

    private UserServiceInvocation userServiceInvocation;

    @Autowired
    public PostService(PostRepository postRepository, UserServiceInvocation userServiceInvocation) {
        this.postRepository = postRepository;
        this.userServiceInvocation = userServiceInvocation;
    }

    public Post createPost(Post post) {
        User user = userServiceInvocation.getUserById(post.getAuthorId());
        if (user != null) {
            post.setId(null);
            Long amountOfPosts = user.getAmountOfPosts() == null ? 1 : user.getAmountOfPosts() + 1;
            user.setAmountOfPosts(amountOfPosts);
            userServiceInvocation.updateUserInfo(user);
            return postRepository.save(post);
        }
        return null;
    }

    public Post updatePost(Post post) {
        Optional<Post> optionalPost = postRepository.findById(post.getId());
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            post.setAuthorId(existingPost.getAuthorId());
            return postRepository.save(post);
        } else {
            return null;
        }
    }

    public Post deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postRepository.delete(optionalPost.get());
            User user = userServiceInvocation.getUserById(post.getAuthorId());
            if (user != null) {
                Long amountOfPosts = user.getAmountOfPosts() == null ? 0 : user.getAmountOfPosts() - 1;
                amountOfPosts = amountOfPosts < 0 ? 0 : amountOfPosts;
                user.setAmountOfPosts(amountOfPosts);
                userServiceInvocation.updateUserInfo(user);
            }
            return post;
        } else {
            return null;
        }
    }

    public Post getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            return optionalPost.get();
        } else {
            return null;
        }
    }
}