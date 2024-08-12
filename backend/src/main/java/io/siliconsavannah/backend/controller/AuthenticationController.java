package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.LoginDto;
import io.siliconsavannah.backend.dto.SignUpDto;
import io.siliconsavannah.backend.service.AuthenticationService;
import io.siliconsavannah.backend.service.JwtService;
import io.siliconsavannah.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpDto request){
        try{
            return ResponseEntity.status(201).body(authenticationService.signUp(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("failed to Sign up new User "+e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request){
        try{
            return ResponseEntity.ok(authenticationService.login(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("failed to login User "+e.getMessage());
        }
    }
}
