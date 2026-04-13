package com.diaza.habitracker.controllers;


import com.diaza.habitracker.entities.User;
import com.diaza.habitracker.payload.response.MessageResponse;
import com.diaza.habitracker.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {


    @Autowired
    private UserServices services;

    @GetMapping("users")
    public ResponseEntity<?> findAll() {
        return  ResponseEntity.ok(services.findAll());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = services.findById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("User found, ID: " + user.toString()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found"));
    }
    @PostMapping("users")
    public ResponseEntity<?> save(@RequestBody User user) {
        return ResponseEntity.ok(services.save(user));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> save(@PathVariable Long id) {
        User user = services.findById(id);
        if (user != null) {
            services.delete(user);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("User deleted"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found"));
    }
}
