package org.mosesidowu.smeecommerce.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.dtos.requests.CreateSubAdminRequest;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.security.JwtUtil;
import org.mosesidowu.smeecommerce.services.JwtService;
import org.mosesidowu.smeecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Validated
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final JwtService jwtService;
    private final JwtUtil jwtUtil;



    @PostMapping("/create-sub-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createSubAdmin(@RequestBody CreateSubAdminRequest request) {
        userService.createSubAdmin(request);
        return ResponseEntity.ok("Sub-admin created and credentials sent");
    }

    @PatchMapping("/block-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> blockUser(@RequestParam String email) {
        userService.disableUser(email);
        return ResponseEntity.ok("User has been blocked");
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


}
