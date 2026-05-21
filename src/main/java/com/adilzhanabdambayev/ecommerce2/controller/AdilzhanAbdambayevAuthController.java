package com.adilzhanabdambayev.ecommerce2.controller;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevAuthResponse;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevLoginRequest;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevRegisterRequest;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AdilzhanAbdambayevAuthController {

    private final AdilzhanAbdambayevAuthService authService;

    public AdilzhanAbdambayevAuthController(AdilzhanAbdambayevAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AdilzhanAbdambayevAuthResponse> register(
            @Valid @RequestBody AdilzhanAbdambayevRegisterRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AdilzhanAbdambayevAuthResponse> login(
            @Valid @RequestBody AdilzhanAbdambayevLoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
