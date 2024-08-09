package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.UserDto;
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
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.readAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto>updateUser(@RequestBody UserDto user){
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}