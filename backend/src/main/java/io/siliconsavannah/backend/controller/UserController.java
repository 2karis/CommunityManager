package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;


    @GetMapping("/readall")
    public Flux<UserDto> getAllUsers(){
        return userService.readAllUsers();
    }

    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<UserDto>> getUser(@PathVariable("id") int id){
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<UserDto>> createUser(@RequestBody Mono<UserDto> user){
        return userService.createUser(user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<UserDto>> updateUser(@RequestBody Mono<UserDto> user){
        return userService.updateUser(user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<?> deleteUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }
}