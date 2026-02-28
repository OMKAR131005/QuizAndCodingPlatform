package org.pm.quizapp.authentication.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pm.quizapp.authentication.dto.LoginRequest;
import org.pm.quizapp.authentication.dto.RegistrationRequest;
import org.pm.quizapp.authentication.entity.Role;
import org.pm.quizapp.authentication.entity.User;
import org.pm.quizapp.authentication.repository.UserRepository;
import org.pm.quizapp.authentication.service.JwtUtil;
import org.pm.quizapp.authentication.service.UserDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest registrationRequest){
        User user=new User();
        user.setActive(true);
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());
        user.setRole(Role.USER);
        user.setName(registrationRequest.getName());
        userRepository.save(user);
        return ResponseEntity.ok("Registration successful for user name :"+user.getUsername());
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
        Optional<User>user=userRepository.findByUsername(loginRequest.getUsername());
        if(!user.get().isActive()){
           return ResponseEntity.ok( "this account is ban by system due to several reason");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        return ResponseEntity.ok(JwtUtil.generateToken(user.get()));

    }
}
