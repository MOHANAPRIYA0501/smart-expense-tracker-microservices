package com.mona.expensetracker.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mona.expensetracker.auth.config.JwtService;
import com.mona.expensetracker.auth.dto.LoginRequest;
import com.mona.expensetracker.auth.dto.RegisterRequest;
import com.mona.expensetracker.auth.entity.User;
import com.mona.expensetracker.auth.repository.UserRepository;
@Service
public class AuthService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())) ;
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "User registered successfully";
    }
    public String login(LoginRequest request) {

    Optional<User> optionalUser =
            userRepository.findByEmail(request.getEmail());

    if (optionalUser.isEmpty()) {
        return "User not found";
    }

    User user = optionalUser.get();

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword())) {

        return "Invalid password";
    }

    return jwtService.generateToken(user.getEmail());
}
}