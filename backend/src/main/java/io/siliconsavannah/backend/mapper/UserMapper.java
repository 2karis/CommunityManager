package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getType(),
            user.getPhone(),
            user.getCreatedAt(),
            user.getUpdatedAt(),
            user.getTasks()
        );
    }
}