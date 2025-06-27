package org.mosesidowu.smeecommerce.controller;

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

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Validated
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;


    @PostMapping("/create-sub-admin")
    public ResponseEntity<?> createSubAdmin(@RequestBody CreateSubAdminRequest request) {
        try {
            userService.createSubAdmin(request);
            return new ResponseEntity<>(new ApiResponse("Sub-admin created and credentials sent", true), HttpStatus.CREATED);
        }
        catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/block-user")
    public ResponseEntity<?> blockUser(@RequestParam String email) {
        try {
            userService.disableUser(email);
            return new ResponseEntity<>(new ApiResponse("User blocked", true), HttpStatus.OK);
        }
        catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-user-by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        try {
            UserRegisterResponseDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(new ApiResponse(user, true));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), false));
        }
    }


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<UserRegisterResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse(users, true));
    }


    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam String email) {
        try {
            userService.deleteUser(email);
            return new ResponseEntity<>(new ApiResponse("User deleted successfully", true ), HttpStatus.OK);
        }
        catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}

