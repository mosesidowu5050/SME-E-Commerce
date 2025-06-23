package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.exception.InvalidEmailException;
import org.mosesidowu.smeecommerce.exception.InvalidFullNameException;
import org.mosesidowu.smeecommerce.exception.InvalidPhoneNumberException;
import org.mosesidowu.smeecommerce.exception.InvalidRoleException;

public class Mapper {

    public static User registerUserRequest(UserRegistrationRequestDTO userRegistrationRequest) {
        validateEmail(userRegistrationRequest.getEmail());
        validatePhoneNumber(userRegistrationRequest.getPhoneNumber());
        validateFullName(userRegistrationRequest.getFullName());
        validateRole(userRegistrationRequest.getRole().name());

        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setFullName(userRegistrationRequest.getFullName());
        user.setRole(userRegistrationRequest.getRole());

        return user;
    }


    private static void validateEmail(String email) {
        if (email == null || email.isEmpty()) throw new InvalidEmailException("Email cannot be null or empty");
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) throw new InvalidEmailException("Invalid email format");
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) throw new InvalidEmailException("Phone number cannot be null or empty");
        if (!phoneNumber.matches("^\\+?[0-9]{10,15}$")) throw new InvalidPhoneNumberException("Invalid phone number format");
    }
    private static void validateFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) throw new InvalidEmailException("Full name cannot be null or empty");
        if (!fullName.matches("^[a-zA-Z\\s]+$")) throw new InvalidFullNameException("Full name must contain only letters and spaces");
    }

    private static void validateRole(String role) {
        if (role == null || role.isEmpty()) throw new InvalidEmailException("Role cannot be null or empty");
        if (!role.matches("^(CUSTOMER | BUYER |ADMIN)$")) throw new InvalidRoleException("Invalid role. Must be CUSTOMER, BUYER or ADMIN");
    }

}
