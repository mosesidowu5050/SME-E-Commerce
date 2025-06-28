package org.mosesidowu.smeecommerce.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.Role;

@Data
public class UserRegistrationRequestDTO {

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain letters and spaces")
    private String fullName;

    @Email(message = "invalid email")
    @NotBlank(message = "email is required")
    private String email;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Phone number must be 10–15 digits")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+]{8,}$",
            message = "Password must include letters and at least one number"
    )
    private String password;
    private Role role;

}
