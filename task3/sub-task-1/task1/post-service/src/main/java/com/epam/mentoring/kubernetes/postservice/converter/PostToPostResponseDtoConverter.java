package com.epam.mentoring.kubernetes.postservice.converter;

import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

import com.epam.mentoring.kubernetes.postservice.domain.Post;
import com.epam.mentoring.kubernetes.postservice.dto.response.PostResponseDto;

/**
 * @author Eduard_Sardyka
 */
public class PostToPostResponseDtoConverter implements Converter<Post, PostResponseDto> {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Override
    public PostResponseDto convert(Post post) {
        return new PostResponseDto(post.getId(), post.getAuthorId(), post.getText(), post.getPostedAt().format(FORMATTER));
    }

}