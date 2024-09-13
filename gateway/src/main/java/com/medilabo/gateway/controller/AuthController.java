package com.medilabo.gateway.controller;

import com.medilabo.gateway.model.AuthenticationRequest;
import com.medilabo.gateway.model.AuthenticationResponse;
import com.medilabo.gateway.model.CustomUser;
import com.medilabo.gateway.service.JwtService;
import com.medilabo.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final String jwt = jwtService.createJwtToken(authenticationRequest);
        return ResponseEntity.ok(jwt);
    }
}
