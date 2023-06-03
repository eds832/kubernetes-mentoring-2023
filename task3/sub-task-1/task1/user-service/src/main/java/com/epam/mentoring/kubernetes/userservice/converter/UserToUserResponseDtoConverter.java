package com.epam.mentoring.kubernetes.userservice.converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.mentoring.kubernetes.userservice.domain.User;
import com.epam.mentoring.kubernetes.userservice.dto.response.UserResponseDto;

/**
 * @author Eduard_Sardyka
 */
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getAmountOfPosts());
    }

}