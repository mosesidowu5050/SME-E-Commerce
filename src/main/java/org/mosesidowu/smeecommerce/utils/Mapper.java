package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.mosesidowu.smeecommerce.exception.InvalidEmailException;
import org.mosesidowu.smeecommerce.exception.InvalidFullNameException;
import org.mosesidowu.smeecommerce.exception.InvalidPhoneNumberException;
import org.mosesidowu.smeecommerce.exception.InvalidRoleException;

public class Mapper {

    public static User registerUserRequest(UserRegistrationRequestDTO userRegistrationRequest) {
        isUserDetailsValid(userRegistrationRequest);

        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setFullName(userRegistrationRequest.getFullName());
        user.setRole(userRegistrationRequest.getRole());

        return user;
    }

    public static UserRegisterResponseDTO getUserResponse(User savedUser) {
        UserRegisterResponseDTO response = new UserRegisterResponseDTO();
        response.setUserId(savedUser.getUserId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setPhoneNumber(savedUser.getPhoneNumber());
        response.setRole(Role.valueOf(savedUser.getRole().name()));

        return response;
    }


    private static void isUserDetailsValid(UserRegistrationRequestDTO userRegistrationRequest) {
        validateEmail(userRegistrationRequest.getEmail());
        validatePhoneNumber(userRegistrationRequest.getPhoneNumber());
        validateFullName(userRegistrationRequest.getFullName());
        validateRole(userRegistrationRequest.getRole().name());
    }


    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new InvalidEmailException("Email cannot be null or empty");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new InvalidEmailException("Invalid email format");
    }


    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) throw new InvalidEmailException("Phone number cannot be null or empty");
        if (!phoneNumber.matches("^\\+?[0-9]{10,15}$"))
            throw new InvalidPhoneNumberException("Invalid phone number format");
    }

    private static void validateFullName(String fullName) {
        if (fullName == null) throw new InvalidEmailException("Full name cannot be null or empty");
        if (!fullName.matches("^[a-zA-Z\\s]+$"))
            throw new InvalidFullNameException("Full name must contain only letters and spaces");
    }

    private static void validateRole(String role) {
        if (!role.matches("^(CUSTOMER|BUYER|ADMIN)$"))
            throw new InvalidRoleException("Invalid role. Must be CUSTOMER, BUYER or ADMIN");
    }
}
