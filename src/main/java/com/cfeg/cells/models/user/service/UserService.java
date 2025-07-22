package com.cfeg.cells.models.user.service;

import com.cfeg.cells.models.user.dto.RegisterRequest;
import com.cfeg.cells.models.user.dto.RegisterResponse;
import com.cfeg.cells.models.user.entity.User;
import com.cfeg.cells.models.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse registerUser (RegisterRequest registerRequest){
        if (userRepository.existsByEmail(registerRequest.email())){
            throw new RuntimeException("User alredy exists");
        }
        User user = new User();
        user.setName(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setPhone(registerRequest.phone());
        user.setRole(registerRequest.role());
        user.setBirthDate(registerRequest.birthDate());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return new RegisterResponse(savedUser.getName(), savedUser.getEmail(), savedUser.getPhone(), savedUser.getRole(), savedUser.getBirthDate());
    }
}
