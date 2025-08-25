package com.equipo11.petcare.controller;

import com.equipo11.petcare.dto.AuthRequest;
import com.equipo11.petcare.dto.AuthResponse;
import com.equipo11.petcare.dto.RegisterRequest;
import com.equipo11.petcare.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/singin")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authUser(request));
    }

    @PostMapping("singup")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
