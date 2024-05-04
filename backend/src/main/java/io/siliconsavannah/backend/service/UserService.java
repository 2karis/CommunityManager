package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.TaskDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.mapper.UserMapper;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepo userRepo;


    public Mono<UserDto> createUser(Mono<UserDto> userDto){
        return userDto
                .map(userMapper::dtoToEntity)
                .map(userRepo::save)
                .map(userMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<UserDto> readAllUsers(){
        return Flux.fromStream(() -> userRepo.findAll().stream())
                .map(userMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<UserDto> updateUser(Mono<UserDto> userDto){
        return userDto
                .flatMap(dto-> Mono.fromSupplier(()->userRepo.findById(dto.id()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(entity->{
                            if (dto.firstName()!= null) entity.setFirstName(dto.firstName());
                            if (dto.lastName()!= null) entity.setLastName(dto.lastName());
                            if (dto.email()!= null) entity.setEmail(dto.email());
                            if (dto.password()!= null) entity.setPassword(dto.password());
                            if (dto.role()!= null) entity.setRole(dto.role());
                            if (dto.phone()!= null) entity.setPhone(dto.phone());
                            if (dto.imageUrl()!= null) entity.setImageUrl(dto.imageUrl());
                            if (dto.tasks()!= null) entity.setTasks(dto.tasks());
                            return userRepo.save(entity);
                        })
                )
                .filter(Objects::nonNull)
                .map(userMapper::entityToDto)
                .switchIfEmpty(Mono.error(Throwable::new))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteUser(int id){
        return Mono.fromRunnable(()->{userRepo.deleteById(id);})
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<UserDto> findUserById(int id){
        return Mono.fromSupplier(()->userRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(userMapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
