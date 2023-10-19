package com.epam.mentoring.kubernetes.postservice.converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.mentoring.kubernetes.postservice.domain.User;
import com.epam.mentoring.kubernetes.postservice.dto.response.UserResponseDto;

/**
 * @author Eduard_Sardyka
 */
public class UserResponseDtoToUserConverter implements Converter<UserResponseDto, User> {

    @Override
    public User convert(UserResponseDto userResponseDto) {
        return new User(userResponseDto.getId(), userResponseDto.getUsername(), userResponseDto.getAmountOfPosts());
    }

}