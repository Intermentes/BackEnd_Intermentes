package br.com.intermentes.intermentesapi.controllers;

import br.com.intermentes.intermentesapi.dto.*;
import br.com.intermentes.intermentesapi.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest loginRequest) {
        JwtAuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUp signUp) {
        return ResponseEntity.ok().body(authService.signUp(signUp));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(authService.getUserById(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody SignUp signUp) {
        return ResponseEntity.ok().body(authService.updateUser(id, signUp));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = authService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }


}
