package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.mapper.UserMapper;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepo userRepo;


    public UserDto createUser(UserDto user){
        return userMapper.entityToDto(userRepo.save(userMapper.dtoToEntity(user)));
    }

    public List<UserDto> readAllUsers(){
        return userRepo.findAll().stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto dto) throws Exception {
        User entity = userRepo.findUserById(dto.id())
                .orElseThrow(() -> new Exception("user with id "+ dto.id() +" not found"));

        if (dto.firstName()!= null) entity.setFirstName(dto.firstName());
        if (dto.lastName()!= null) entity.setLastName(dto.lastName());
        if (dto.email()!= null) entity.setEmail(dto.email());
        if (dto.password()!= null) entity.setPassword(dto.password());
        if (dto.role()!= null) entity.setRole(dto.role());
        if (dto.phone()!= null) entity.setPhone(dto.phone());
        if (dto.imageUrl()!= null) entity.setImageUrl(dto.imageUrl());
        if (dto.tasks()!= null) entity.setTasks(dto.tasks());

        return userMapper.entityToDto(userRepo.save(entity));
    }
    public void deleteUser(int id){
        userRepo.deleteUserById(id);
    }

    public UserDto findUserById(int id) throws Exception {
        return userMapper.entityToDto(userRepo.findUserById(id)
                .orElseThrow(() -> new Exception("user with id "+ id +" not found")));
    }
}
