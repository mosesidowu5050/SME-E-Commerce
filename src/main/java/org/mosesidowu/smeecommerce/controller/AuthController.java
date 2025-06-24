package org.mosesidowu.smeecommerce.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.security.JwtUtil;
import org.mosesidowu.smeecommerce.services.JwtService;
import org.mosesidowu.smeecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController  {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequestDTO registerUserRequest) {
        try {
            UserRegisterResponseDTO response = userService.register(registerUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(response, true));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false));
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequestDTO loginUserRequest) {
        try {
            JwtResponse response = userService.login(loginUserRequest);
            return ResponseEntity.ok(new ApiResponse(response, true));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false));
        }
    }



    @GetMapping("/get-user")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(new ApiResponse("Missing or invalid token", false));
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);
        try {
            UserRegisterResponseDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(new ApiResponse(user, true));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), false));
        }
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return ResponseEntity.badRequest().body("Invalid token");
        String token = authHeader.substring(7);
        jwtService.blacklistToken(token);

        return ResponseEntity.ok("Logout successful");
    }
}
