package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ={"http://localhost:4200", "http://localhost:80"})
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
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto>updateUser(@RequestBody UserDto user){
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
