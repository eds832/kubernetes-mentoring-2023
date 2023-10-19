package com.epam.mentoring.kubernetes.postservice.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;

import com.epam.mentoring.kubernetes.postservice.domain.Post;
import com.epam.mentoring.kubernetes.postservice.dto.request.PostRequestDto;

/**
 * @author Eduard_Sardyka
 */
public class PostRequestDtoToPostConverter implements Converter<PostRequestDto, Post> {

    @Override
    public Post convert(PostRequestDto postRequestDto) {
        return new Post(postRequestDto.getId(), postRequestDto.getAuthorId(), postRequestDto.getText(), LocalDate.now());
    }

}