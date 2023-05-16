package com.mcss.simpleapi.Controller;

import com.mcss.simpleapi.Security.Config.JWTService;
import com.mcss.simpleapi.DTO.UserRequest;
import com.mcss.simpleapi.DTO.UserResponse;
import com.mcss.simpleapi.Enum.Role;
import com.mcss.simpleapi.Exceptions.UserNotFoundException;
import com.mcss.simpleapi.Model.User;
import com.mcss.simpleapi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class MainController {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        User user = User.builder()
                .age(userRequest.getAge())
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .surname(userRequest.getSurname())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.valueOf(userRequest.getRole().toUpperCase()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(UserResponse.builder()
                .token(jwtToken)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestParam String username, @RequestParam String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,password
                )
        );
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: "+username));
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(UserResponse.builder()
                .token(jwtToken)
                .build());
    }

}
