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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Validated
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final JwtService jwtService;
    private final JwtUtil jwtUtil;



    @PostMapping("/create-sub-admin")
    public ResponseEntity<?> createSubAdmin(@RequestBody CreateSubAdminRequest request) {
        userService.createSubAdmin(request);
        return ResponseEntity.ok("Sub-admin created and credentials sent");
    }


    @PatchMapping("/block-user")
    public ResponseEntity<?> blockUser(@RequestParam String email) {
        userService.disableUser(email);
        return ResponseEntity.ok("User has been blocked");
    }


    @GetMapping("/get-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getUserProfile() {
        try {
            UserRegisterResponseDTO user = userService.getCurrentUser();
            return ResponseEntity.ok(new ApiResponse(user, true));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(), false));
        }
    }




}
