package com.cfeg.cells.controller.user;

import com.cfeg.cells.models.user.dto.RegisterRequest;
import com.cfeg.cells.models.user.dto.RegisterResponse;
import com.cfeg.cells.models.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerUser = userService.registerUser(registerRequest);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

}
