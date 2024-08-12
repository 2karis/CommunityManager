package io.siliconsavannah.backend.service;

import io.siliconsavannah.backend.dto.AuthDto;
import io.siliconsavannah.backend.dto.SignUpDto;
import io.siliconsavannah.backend.dto.LoginDto;
import io.siliconsavannah.backend.dto.UserDto;
import io.siliconsavannah.backend.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthenticationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    public AuthDto signUp(SignUpDto request) throws Exception {
        if(userService.hasUserWithEmail(request.email())){
            throw new Exception("User already Exists");
        }else{
            userService.signUpUser(request);

            final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(request.email());
            HashMap<String,Object> roles= new HashMap<>();
//            String userRole =  userDetails.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority).toList().getFirst();
            roles.put("scope", userDetails.getAuthorities());
            final String jwt = jwtService.generateToken(roles,userDetails);
        return AuthDto.builder()
                .token(jwt)
                .build();

//    String generateBasicToken(String username, String password){
//        String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
//        return "Basic " + encoding;
            //return null;
        }
        //return null;
    }
    public AuthDto login(LoginDto request)throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),
                    request.password()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect email or password.");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(request.email());
        HashMap<String,Object> roles= new HashMap<>();
//        String userRole =  userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).toList().getFirst();
        roles.put("scope", userDetails.getAuthorities());
        final String jwt = jwtService.generateToken(roles,userDetails);
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        return AuthDto.builder()
                .email(userDetails.getUsername())
                .role(roleList.getFirst())
                .token(jwt)
                .build();
    }

//    public static boolean hasRole (String roleName)
//    {
//        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
//    }
}