package com.cfeg.cells.controller.auth;

import com.cfeg.cells.infra.security.TokenService;
import com.cfeg.cells.models.user.dto.LoginRequest;
import com.cfeg.cells.models.user.dto.LoginResponse;
import com.cfeg.cells.models.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> auth(@RequestBody @Valid LoginRequest dataAutenticationUser) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAutenticationUser.email(), dataAutenticationUser.password()
        );

        Authentication authUser = authenticationManager.authenticate(authToken);
        User user = (User) authUser.getPrincipal();
        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}

