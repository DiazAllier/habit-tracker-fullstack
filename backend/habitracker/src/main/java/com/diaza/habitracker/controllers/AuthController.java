package com.diaza.habitracker.controllers;


import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public String register(@RequestBody User request) {
        return authService.register(request.getEmail(), request.getPassword());
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User request) {
//        return authService.login(request.getEmail(), request.getPassword());
        String token = authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
