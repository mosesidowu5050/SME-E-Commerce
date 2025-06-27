package org.mosesidowu.smeecommerce.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSubAdminRequest {
    @NotBlank(message = "name is required")
    private String fullName;

    @NotBlank(message = "email is required")
    @Email(message = "Enter a valid email")
    private String email;

}
