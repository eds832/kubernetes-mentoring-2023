package com.epam.mentoring.kubernetes.postservice.converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.mentoring.kubernetes.postservice.domain.User;
import com.epam.mentoring.kubernetes.postservice.dto.request.UserRequestDto;

/**
 * @author Eduard_Sardyka
 */
public class UserToUserRequestDtoConverter implements Converter<User, UserRequestDto> {

    @Override
    public UserRequestDto convert(User user) {
        return new UserRequestDto(user.getId(), user.getUsername(), user.getAmountOfPosts());
    }

}