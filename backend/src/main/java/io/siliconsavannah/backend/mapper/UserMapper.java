package io.siliconsavannah.backend.mapper;

import io.siliconsavannah.backend.dto.LeaseDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.model.Lease;
import io.siliconsavannah.backend.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface UserMapper {
    UserDto entityToDto(User entity);
    User dtoToEntity(UserDto dto);

}