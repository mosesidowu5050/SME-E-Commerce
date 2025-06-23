package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.Role;

@Data
public class UserRegistrationRequestDTO {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;

}
