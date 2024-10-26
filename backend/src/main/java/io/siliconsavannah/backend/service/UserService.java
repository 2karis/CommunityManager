package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.PasswordDto;
import io.siliconsavannah.backend.dto.SignUpDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.enums.Role;
import io.siliconsavannah.backend.mapper.UserMapper;
import io.siliconsavannah.backend.model.User;
import io.siliconsavannah.backend.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public UserDto createUser(UserDto user){
        return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(user)));
    }

    public List<UserDto> readAllUsers(){
        return userRepository.findAll().stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto dto) throws Exception {
        User entity = userRepository.findUserById(dto.id())
                .orElseThrow(() -> new Exception("user with id "+ dto.id() +" not found"));

        if (dto.firstName()!= null) entity.setFirstName(dto.firstName());
        if (dto.lastName()!= null) entity.setLastName(dto.lastName());
        if (dto.email()!= null) entity.setEmail(dto.email());
        if (dto.phone()!= null) entity.setPhone(dto.phone());
        if (dto.imageUrl()!= null) entity.setImageUrl(dto.imageUrl());
        if (dto.tasks()!= null) entity.setTasks(dto.tasks());

        return userMapper.entityToDto(userRepository.save(entity));
    }
    public void deleteUser(int id){
        userRepository.deleteUserById(id);
    }

    public UserDto findUserById(int id) throws Exception {
        return userMapper.entityToDto(userRepository.findUserById(id)
                .orElseThrow(() -> new Exception("user with id "+ id +" not found")));
    }

    public UserDto signUpUser(SignUpDto request){

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.COMMUNITY)
                .build();

        User newUser = userRepository.save(user);
        List<String> roles = newUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        return  UserDto.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    public boolean updateUserPassword(PasswordDto passwordDto) {
        try{
            User user = User.builder()
                    .password(passwordEncoder.encode(passwordDto.password()))
                    .build();
            User newUser = userRepository.save(user);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepository.findFirstByEmail(email)
                        .orElseThrow(()-> new UsernameNotFoundException("User not found!"));
            }
        };
    }
}
