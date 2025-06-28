package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.dtos.requests.CreateSubAdminRequest;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.mosesidowu.smeecommerce.exception.InvalidEmailException;
import org.mosesidowu.smeecommerce.exception.InvalidFullNameException;
import org.mosesidowu.smeecommerce.exception.InvalidPhoneNumberException;
import org.mosesidowu.smeecommerce.exception.InvalidRoleException;

import java.util.Random;

public class Mapper {

    public static User registerUserRequest(UserRegistrationRequestDTO userRegistrationRequest) {
        isUserDetailsValid(userRegistrationRequest);

        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setFullName(userRegistrationRequest.getFullName());
        user.setRole(userRegistrationRequest.getRole());
        user.setEnabled(true);

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


    public static void isUserDetailsValid(UserRegistrationRequestDTO userRegistrationRequest) {
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
        if (!role.matches("^(CUSTOMER|SELLER|ADMIN)$"))
            throw new InvalidRoleException("Invalid role. Must be CUSTOMER, BUYER or ADMIN");
    }



    public static User getSubAminResponse(CreateSubAdminRequest request, String hashedPassword) {
        validateFullName(request.getFullName());
        validateEmail(request.getEmail());

        User subAdmin = new User();
        subAdmin.setEmail(request.getEmail());
        subAdmin.setFullName(request.getFullName());
        subAdmin.setPassword(hashedPassword);
        subAdmin.setRole(Role.ADMIN);
        subAdmin.setEnabled(true);

        return subAdmin;
    }


    public static String generateRandomPassword() {
        char[] generateToken = {
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };

        String savedToken = "";

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomToken = random.nextInt(generateToken.length);
            savedToken += generateToken[randomToken];
        }

        return savedToken;
    }

}
