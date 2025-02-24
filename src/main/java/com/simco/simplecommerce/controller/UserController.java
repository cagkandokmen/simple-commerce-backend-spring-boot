package com.simco.simplecommerce.controller;

import com.simco.simplecommerce.dto.TokenDTO;
import com.simco.simplecommerce.dto.UserDTO;
import com.simco.simplecommerce.service.AuthService;
import com.simco.simplecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDTO user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login( @RequestBody UserDTO user) {
        TokenDTO token =authService.authenticate(user);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
