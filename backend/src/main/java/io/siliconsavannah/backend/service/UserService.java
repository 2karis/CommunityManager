package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.mapper.UserMapper;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user){
        return userRepo.save(user);
    }

    public List<UserDto> readAllUsers(){
        return userRepo.findAll().stream().map(userMapper).collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto){
        Optional<User> user = userRepo.findById(userDto.id());
        user.ifPresent(
                el ->{
                    if (userDto.firstName()!= null) el.setFirstName(userDto.firstName());

                    if (userDto.lastName()!= null) el.setLastName(userDto.lastName());
                    if (userDto.email()!= null) el.setEmail(userDto.email());
                    if (userDto.password()!= null) el.setPassword(userDto.password());
                    if (userDto.type()!= null) el.setType(userDto.type());
                    if (userDto.phone()!= null) el.setPhone(userDto.phone());
                    if (userDto.tasks()!= null) el.setTasks(userDto.tasks());

                    userRepo.save(el);
                }
        );
        return userDto;
    }
    public void deleteUser(int id){
        userRepo.deleteById(id);
    }

    public UserDto findUserById(int id){
        return userRepo.findById(id).stream().map(userMapper).findAny().orElseThrow(RuntimeException::new);
    }
}
