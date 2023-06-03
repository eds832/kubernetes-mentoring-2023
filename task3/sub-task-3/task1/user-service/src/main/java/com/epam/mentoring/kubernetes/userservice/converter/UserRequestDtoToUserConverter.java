package com.epam.mentoring.kubernetes.userservice.converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.mentoring.kubernetes.userservice.domain.User;
import com.epam.mentoring.kubernetes.userservice.dto.request.UserRequestDto;

/**
 * @author Eduard_Sardyka
 */
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto userRequestDto) {
        return new User(userRequestDto.getId(), userRequestDto.getUsername(), userRequestDto.getAmountOfPosts());
    }

}